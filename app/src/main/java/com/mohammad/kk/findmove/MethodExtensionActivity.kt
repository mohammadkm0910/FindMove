package com.mohammad.kk.findmove

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mohammad.kk.findmove.Helper.*
import com.mohammad.kk.findmove.util.Extension.eval
import com.mohammad.kk.findmove.util.Extension.getImageInfo
import com.mohammad.kk.findmove.util.Extension.getUrl
import com.mohammad.kk.findmove.util.Extension.initializedPrivateWebView
import com.mohammad.kk.findmove.util.Extension.isInternet
import com.mohammad.kk.findmove.util.Extension.loadUrl
import com.mohammad.kk.findmove.util.Extension.toEnNumbers
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.Extension.toSeparatorComma
import kotlinx.android.synthetic.main.activity_method_extension.*
import kotlinx.android.synthetic.main.extension_method_dialog.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MethodExtensionActivity : AppCompatActivity() {
    private lateinit var behavior:BottomSheetBehavior<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_method_extension)
        setSupportActionBar(toolbarApp)
        setCropImage(isCropImage)

        setupTab()
        setupKeyboard()
        getUrlImage()
        progressRefreshActivity.visibility = View.INVISIBLE
        if (!isCropImage) loadImageInternet.scaleType = ImageView.ScaleType.FIT_CENTER else loadImageInternet.scaleType = ImageView.ScaleType.CENTER_CROP
        registerForContextMenu(loadImageInternet)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    100
                )
            }
        }
        expressionEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                expressionTextView.text = setupText(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        loadImageInternet.loadUrl(if (!isInternet(this)) DEFAULT_URL_LOCAL else DEFAULT_URL_IMAGE)
    }
    private fun setupTab() {
        tabBottom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.extensionFunText -> {
                    tabText.visibility = View.VISIBLE
                    tabImage.visibility = View.GONE
                }
                R.id.extensionFunImage -> {
                    tabText.visibility = View.GONE
                    tabImage.visibility = View.VISIBLE
                }
            }
            true
        }
    }
    private fun setupKeyboard() {
        val ic = expressionEditText.onCreateInputConnection(EditorInfo())
        keyApp.setInputConnection(ic)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(0, 1, 1, "صفحه قبلی").setIcon(R.drawable.ic_arrow_left).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.add(0, 2, 2, "بازیابی صفحه").setIcon(R.drawable.ic_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.add(0, 3, 3, "منو").setIcon(R.drawable.ic_menu).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> onBackPressed()
            2 -> {
                progressRefreshActivity.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    expressionEditText.inputType =
                        InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                    expressionEditText.clearFocus()
                    expressionEditText.text.clear()
                    expressionTextView.text = ""
                    TXT_CONFIG = 1
                    progressRefreshActivity.visibility = View.INVISIBLE
                    isCropImage = false
                    setCropImage(isCropImage)
                    loadImageInternet.loadUrl(if (!isInternet(this)) DEFAULT_URL_LOCAL else DEFAULT_URL_IMAGE)
                    val imm =
                        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(expressionEditText.windowToken, 0)
                }, 1000)
            }
            3 -> {
                val bottomSheetDialog = BottomSheetDialog(this)
                val view = LayoutInflater.from(this).inflate(
                    R.layout.extension_method_dialog, findViewById(
                        R.id.navExtensionDialog
                    )
                )
                view.navExtensionDialog.setNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.separatorNumber -> {
                            TXT_CONFIG = 1
                            expressionTextView.text = setupText(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        R.id.faNumber -> {
                            TXT_CONFIG = 2
                            expressionTextView.text = setupText(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        R.id.enEnglish -> {
                            TXT_CONFIG = 3
                            expressionTextView.text = setupText(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        R.id.calculatorExpression -> {
                            TXT_CONFIG = 4
                            expressionTextView.text = setupText(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                        }
                    }
                    bottomSheetDialog.dismiss()
                    true
                }
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.add(0, Menu.FIRST, Menu.FIRST, "آدرس عکس").setOnMenuItemClickListener {
            Toast.makeText(this, loadImageInternet.getUrl(), Toast.LENGTH_LONG).show()
            true
        }
        menu.add(0, Menu.FIRST + 1, Menu.FIRST + 1, "مشخصات عکس").setOnMenuItemClickListener {
            Toast.makeText(this, loadImageInternet.getImageInfo(), Toast.LENGTH_LONG).show()
            true
        }
        menu.add(0, Menu.FIRST + 2, Menu.FIRST + 2, "برش عکس").setCheckable(true).setChecked(
            isCropImage
        ).setOnMenuItemClickListener {
            isCropImage = !isCropImage
            setCropImage(isCropImage)
            true
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    private fun setupText(text: String) = when(TXT_CONFIG){
        1 -> text.toSeparatorComma()
        2 -> text.toFaNumbers()
        3 -> text.toEnNumbers()
        4 -> text.eval().toString()
        else -> text
    }
    private fun getUrlImage(){
        val movies = arrayOf(
            godfatherName, cityOfGodsName, millionDollarBabyName,
            theIrishmanName, heatName, arrivalName,
            theDropName, onceUponATimeInHollywoodName, roomName,
            whiplashName,
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies)
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
        btnClearUrl.setOnClickListener {
            edtSearchMovie.text.clear()
        }
        btnStorageImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "اتنخاب تصویر"), 100)
        }
        btnUrlImgWebView.setOnClickListener {
            behavior = BottomSheetBehavior.from(webLoad)
            if (behavior.state == BottomSheetBehavior.STATE_COLLAPSED)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            else if (behavior.state == BottomSheetBehavior.STATE_EXPANDED)
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            webLoad.initializedPrivateWebView()
            webLoad.loadUrl("https://www.google.com/imghp?hl=en&tab=wi&ogbl")
            webLoad.webViewClient = WebViewClient()
            webLoad.isFocusable = true
            webLoad.isFocusableInTouchMode = true
            webLoad.setOnLongClickListener {
                val webViewHitTestResult = webLoad.hitTestResult
                if (webViewHitTestResult.type == WebView.HitTestResult.IMAGE_TYPE
                    || webViewHitTestResult.type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE){
                    val imageUrl = webViewHitTestResult.extra
                    loadImageInternet.loadUrl(imageUrl.toString())
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                true
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if (requestCode == 100){
                val selectImg = data!!.data
                if (selectImg != null){
                    loadImageInternet.loadUrl(selectImg.toString())
                }
            }
        }
    }
    private fun setCropImage(enable: Boolean){
        if (!enable) loadImageInternet.scaleType = ImageView.ScaleType.FIT_CENTER else loadImageInternet.scaleType = ImageView.ScaleType.CENTER_CROP
    }
    companion object{
        private var TXT_CONFIG = 1
        private  const val DEFAULT_URL_IMAGE = "http://www.coca.ir/wp-content/uploads/2017/07/beautiful-dream-nature-photos-8.jpg"
        private  const val DEFAULT_URL_LOCAL =  R.drawable.nature_default
        private var isCropImage = false
    }

}