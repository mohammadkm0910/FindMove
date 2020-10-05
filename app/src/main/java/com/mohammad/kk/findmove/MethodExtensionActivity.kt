package com.mohammad.kk.findmove

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.mohammad.kk.findmove.util.Extension.eval
import com.mohammad.kk.findmove.util.Extension.getUrl
import com.mohammad.kk.findmove.util.Extension.isInternet
import com.mohammad.kk.findmove.util.Extension.loadUrl
import com.mohammad.kk.findmove.util.Extension.toEnNumbers
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.Extension.toSeparatorComma
import kotlinx.android.synthetic.main.activity_method_extension.*

class MethodExtensionActivity : AppCompatActivity() {
    private var MODE_TEXT = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_method_extension)
        setSupportActionBar(toolbarExtension)
        val imageButton = ImageButton(this)
        val layoutParent = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParent.gravity = Gravity.END
        layoutParent.setMargins(8,0,0,0)
        imageButton.layoutParams = layoutParent
        imageButton.setImageResource(R.drawable.ic_arrow_down)
        imageButton.rotation = 90F
        imageButton.setBackgroundResource(R.drawable.background_ripple_circle)
        imageButton.imageTintList = ColorStateList.valueOf(Color.WHITE)
        imageButton.setOnClickListener { onBackPressed() }
        toolbarExtension.addView(imageButton)
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
                expressionTextView.text = setupText(MODE_TEXT, s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        expressionEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
        loadImageInternet.setOnClickListener {
            Toast.makeText(this,loadImageInternet.getUrl(),Toast.LENGTH_LONG).show()
        }
        if (!isInternet(this)){
            loadImageInternet.loadUrl(R.drawable.nature_default)
        } else {
            loadImageInternet.loadUrl("http://www.coca.ir/wp-content/uploads/2017/07/beautiful-dream-nature-photos-8.jpg")
        }
        setupDrawer()
    }

    private fun setupDrawer () {
        val actionToggleButton = ActionBarDrawerToggle(this,drawerExtension,toolbarExtension,0,0)
        drawerExtension.addDrawerListener(actionToggleButton)
        actionToggleButton.syncState()
        navExtension.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.separatorNumber ->{
                    MODE_TEXT = 3
                    expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
                    expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                    drawerExtension.closeDrawer(navExtension)
                }
                R.id.faNumber ->{
                    MODE_TEXT = 1
                    expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
                    expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                    drawerExtension.closeDrawer(navExtension)
                }
                R.id.enEnglish ->{
                    MODE_TEXT = 2
                    expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
                    expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                    drawerExtension.closeDrawer(navExtension)
                }
                R.id.calculatorExpression ->{
                    MODE_TEXT = 0
                    expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
                    expressionEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                    drawerExtension.closeDrawer(navExtension)
                }
                R.id.loadPictureInActivity ->{
                    createViewDialog()
                    drawerExtension.closeDrawer(navExtension)
                }
            }
            true
        }
    }
    private fun setupText(config: Int, text: String) = when(config){
        0 -> text.eval().toString()
        1 -> text.toFaNumbers()
        2 -> text.toEnNumbers()
        3 -> text.toSeparatorComma()
        else -> text
    }
    private fun createViewDialog(){
        val view = LayoutInflater.from(this).inflate(
            R.layout.dialog_image_url,
            findViewById(R.id.dialogImgUrl)
        )
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.window!!.attributes.windowAnimations = R.style.DialogFullImage
        val edtListImageMoveDevice = view.findViewById<AutoCompleteTextView>(R.id.edtListImageMoveDevice)
        edtListImageMoveDevice.showSoftInputOnFocus = false
        val sendImageUrlToApp = view.findViewById<MaterialButton>(R.id.sendImageUrlToApp)
        val dialogCloseImageUrl = view.findViewById<AppCompatImageButton>(R.id.dialogCloseImageUrl)
        val storageImage = view.findViewById<AppCompatImageButton>(R.id.storageImage)
        val listMove = arrayOf(
            HelperMoves.godfatherName, HelperMoves.cityOfGodsName, HelperMoves.millionDollarBabyName,
            HelperMoves.theIrishmanName, HelperMoves.heatName, HelperMoves.arrivalName,
            HelperMoves.theDropName, HelperMoves.onceUponATimeInHollywoodName, HelperMoves.roomName,
            HelperMoves.whiplashName,
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listMove)
        edtListImageMoveDevice.setAdapter(adapter)
        sendImageUrlToApp.setOnClickListener {
            if (edtListImageMoveDevice.text.toString().isNotEmpty()){
                when(edtListImageMoveDevice.text.toString()){
                    HelperMoves.godfatherName -> loadImageInternet.loadUrl(R.drawable.godfather)
                    HelperMoves.cityOfGodsName -> loadImageInternet.loadUrl(R.drawable.city_of_gods)
                    HelperMoves.millionDollarBabyName -> loadImageInternet.loadUrl(R.drawable.baby_million_dollar)
                    HelperMoves.theIrishmanName -> loadImageInternet.loadUrl(R.drawable.the_irishman)
                    HelperMoves.heatName -> loadImageInternet.loadUrl(R.drawable.heat)
                    HelperMoves.arrivalName -> loadImageInternet.loadUrl(R.drawable.arrival)
                    HelperMoves.theDropName -> loadImageInternet.loadUrl(R.drawable.the_drop)
                    HelperMoves.onceUponATimeInHollywoodName -> loadImageInternet.loadUrl(R.drawable.once_upon_a_time_in_hollywood)
                    HelperMoves.roomName -> loadImageInternet.loadUrl(R.drawable.room)
                    HelperMoves.whiplashName -> loadImageInternet.loadUrl(R.drawable.whiplash)
                    else -> loadImageInternet.loadUrl(edtListImageMoveDevice.text.toString())
                }
                dialog.dismiss()
            }
        }
        storageImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "اتنخاب تصویر"), 100)
            dialog.dismiss()
        }
        dialogCloseImageUrl.setOnClickListener { dialog.dismiss() }
        dialog.show()
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
}