package com.mohammad.kk.findmove.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.mohammad.kk.findmove.Helper.*
import com.mohammad.kk.findmove.R
import com.mohammad.kk.findmove.util.Extension
import com.mohammad.kk.findmove.util.Extension.getImageInfo
import com.mohammad.kk.findmove.util.Extension.getUrl
import com.mohammad.kk.findmove.util.Extension.loadUrl

class MethodImageFragment: Fragment() {
    private lateinit var edtSearchMovie:AutoCompleteTextView
    private lateinit var btnDoneUrl:ImageButton
    private lateinit var btnStorageImage:ImageButton
    private lateinit var btnUrlImgWebView:ImageButton
    private lateinit var btnClearUrl:ImageButton
    private lateinit var loadImageInternet:ImageView
    private lateinit var progressRefreshCenter:ProgressBar
    private lateinit var imageMethodLayout:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.method_extension_image_fragment,container,false)
        getIds(view)
        getUrl()
        clearUrl()
        registerForContextMenu(loadImageInternet)
        setCropImage(IS_CROP_IMAGE)
        loadImageInternet.loadUrl(if (!Extension.isInternet(context!!)) DEFAULT_URL_LOCAL else DEFAULT_URL_IMAGE)
        return view
    }
    private fun getIds(view: View) {
        edtSearchMovie = view.findViewById(R.id.edtSearchMovie)
        btnDoneUrl = view.findViewById(R.id.btnDoneUrl)
        btnStorageImage = view.findViewById(R.id.btnStorageImage)
        btnUrlImgWebView = view.findViewById(R.id.btnUrlImgWebView)
        btnClearUrl = view.findViewById(R.id.btnClearUrl)
        loadImageInternet = view.findViewById(R.id.loadImageInternet)
        progressRefreshCenter = view.findViewById(R.id.progressRefreshCenter)
        imageMethodLayout = view.findViewById(R.id.imageMethodLayout)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.add(0,1,1,"تازه سازی").setIcon(R.drawable.ic_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 1){
            imageMethodLayout.visibility = View.GONE
            progressRefreshCenter.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                edtSearchMovie.text.clear()
                edtSearchMovie.clearFocus()
                IS_CROP_IMAGE = false
                setCropImage(IS_CROP_IMAGE)
                loadImageInternet.loadUrl(if (!Extension.isInternet(context!!)) DEFAULT_URL_LOCAL else DEFAULT_URL_IMAGE)
                imageMethodLayout.visibility = View.VISIBLE
                progressRefreshCenter.visibility = View.GONE
            },1000)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, Menu.FIRST, Menu.FIRST, "آدرس عکس").setOnMenuItemClickListener {
            Toast.makeText(activity, loadImageInternet.getUrl(), Toast.LENGTH_LONG).show()
            true
        }
        menu.add(0, Menu.FIRST + 1, Menu.FIRST + 1, "مشخصات عکس").setOnMenuItemClickListener {
            Toast.makeText(activity, loadImageInternet.getImageInfo(), Toast.LENGTH_LONG).show()
            true
        }
        menu.add(0, Menu.FIRST + 2, Menu.FIRST + 2, "برش عکس").setCheckable(true).setChecked(IS_CROP_IMAGE).setOnMenuItemClickListener {
            IS_CROP_IMAGE = !IS_CROP_IMAGE
            setCropImage(IS_CROP_IMAGE)
            true
        }
    }
    private fun getUrl() {
        val movies = arrayOf(
            godfatherName, cityOfGodsName, millionDollarBabyName,
            theIrishmanName, heatName, arrivalName,
            theDropName, onceUponATimeInHollywoodName, roomName,
            whiplashName,
        )
        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, movies)
        edtSearchMovie.setAdapter(adapter)
        edtSearchMovie.showSoftInputOnFocus = false
        btnDoneUrl.setOnClickListener {
            if (edtSearchMovie.text.toString().isNotEmpty()){
                when(edtSearchMovie.text.toString()){
                    movies[0] -> loadImageInternet.loadUrl(R.drawable.godfather)
                    movies[1] -> loadImageInternet.loadUrl(R.drawable.city_of_gods)
                    movies[2] -> loadImageInternet.loadUrl(R.drawable.baby_million_dollar)
                    movies[3] -> loadImageInternet.loadUrl(R.drawable.the_irishman)
                    movies[4] -> loadImageInternet.loadUrl(R.drawable.heat)
                    movies[5] -> loadImageInternet.loadUrl(R.drawable.arrival)
                    movies[6] -> loadImageInternet.loadUrl(R.drawable.the_drop)
                    movies[7] -> loadImageInternet.loadUrl(R.drawable.once_upon_a_time_in_hollywood)
                    movies[8] -> loadImageInternet.loadUrl(R.drawable.room)
                    movies[9] -> loadImageInternet.loadUrl(R.drawable.whiplash)
                    else -> loadImageInternet.loadUrl(edtSearchMovie.text.toString())
                }
            }
        }
        btnStorageImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"انتخاب تصویر"),100)
        }
        btnUrlImgWebView.setOnClickListener {
            setupBrowser()
        }
    }
    private fun setupBrowser() {
        val url = "https://www.google.com/imghp?hl=en&tab=wi&ogbl"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(activity!!, Uri.parse(url))
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK){
            if (requestCode == 100){
                val selectImg = data!!.data
                if (selectImg != null){
                    loadImageInternet.loadUrl(selectImg.toString())
                }
            }
        }
    }
    private fun clearUrl() {
        btnClearUrl.setOnClickListener {
            edtSearchMovie.text.clear()
        }
    }
    private fun setCropImage(enable:Boolean) {
        if (!enable) loadImageInternet.scaleType = ImageView.ScaleType.FIT_CENTER else loadImageInternet.scaleType = ImageView.ScaleType.CENTER_CROP
    }
    companion object{
        private  const val DEFAULT_URL_IMAGE = "http://www.coca.ir/wp-content/uploads/2017/07/beautiful-dream-nature-photos-8.jpg"
        private  const val DEFAULT_URL_LOCAL =  R.drawable.nature_default
        private var IS_CROP_IMAGE = false
    }
}