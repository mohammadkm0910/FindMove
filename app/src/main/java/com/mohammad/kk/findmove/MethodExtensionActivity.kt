package com.mohammad.kk.findmove

import android.Manifest
import android.app.DownloadManager
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.mohammad.kk.findmove.util.Extension.eval
import com.mohammad.kk.findmove.util.Extension.getUrl
import com.mohammad.kk.findmove.util.Extension.loadUrl
import com.mohammad.kk.findmove.util.Extension.toEnNumbers
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.Extension.toSeparatorComma
import kotlinx.android.synthetic.main.activity_method_extension.*
import kotlinx.android.synthetic.main.dialog_image_url.*


class MethodExtensionActivity : AppCompatActivity() {
    private var isOpenMenu = false
    private var isEnDigits = false
    private var MODE_TEXT = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_method_extension)
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
        fabMenu.setOnClickListener {
            val open = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.move_up)
            val exit = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.move_down)
            fabMenu.setImageResource(if (!isOpenMenu) R.drawable.ic_close else R.drawable.ic_menu)
            fabCalculator.visibility = if (!isOpenMenu) View.VISIBLE else View.INVISIBLE
            fabCalculator.startAnimation(if (!isOpenMenu) open else exit)
            fabLangNumber.visibility = if (!isOpenMenu) View.VISIBLE else View.INVISIBLE
            fabLangNumber.startAnimation(if (!isOpenMenu) open else exit)
            fabSeparatorComma.visibility = if (!isOpenMenu) View.VISIBLE else View.INVISIBLE
            fabSeparatorComma.startAnimation(if (!isOpenMenu) open else exit)
            isOpenMenu = !isOpenMenu
        }
        fabCalculator.setOnClickListener {
            MODE_TEXT = 0
            expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
            expressionEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            activeFab()
        }
        fabLangNumber.setOnClickListener {
            MODE_TEXT = if (!isEnDigits) 1 else 2
            fabLangNumber.setImageResource(if (!isEnDigits) R.drawable.fa_number else R.drawable.en_number)
            expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
            expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            activeFab()
            isEnDigits = !isEnDigits
        }
        fabSeparatorComma.setOnClickListener {
            MODE_TEXT = 3
            expressionTextView.text = setupText(MODE_TEXT, expressionEditText.text.toString())
            activeFab()
            expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        loadImageInternet.loadUrl("https://arga-mag.com/file/img/2017/04/Canada2.jpg")
        fabLoadImageUrl.setOnClickListener {
            createViewDialog()
        }
        loadImageInternet.setOnClickListener {
            Toast.makeText(this,loadImageInternet.getUrl(),Toast.LENGTH_LONG).show()
        }
    }
    private fun setupText(config: Int, text: String) = when(config){
        0 -> text.eval().toString()
        1 -> text.toFaNumbers()
        2 -> text.toEnNumbers()
        3 -> text.toSeparatorComma()
        else -> text
    }
    private fun activeFab(){
        val activeColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.blueA200))
        val normalColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent))
        when (MODE_TEXT) {
            fabCalculator.tag.toString().toInt() -> fabCalculator.backgroundTintList = activeColor
            else -> fabCalculator.backgroundTintList = normalColor
        }
        when (MODE_TEXT) {
            fabLangNumber.tag.toString().toInt() -> fabLangNumber.backgroundTintList = activeColor
            2 -> fabLangNumber.backgroundTintList = activeColor
            else -> fabLangNumber.backgroundTintList = normalColor
        }
        when (MODE_TEXT) {
            fabSeparatorComma.tag.toString().toInt() -> fabSeparatorComma.backgroundTintList =
                activeColor
            else -> fabSeparatorComma.backgroundTintList = normalColor
        }
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
            HelperMoves.godfatherName,
            HelperMoves.cityOfGodsName,
            HelperMoves.millionDollarBabyName,
            HelperMoves.theIrishmanName,
            HelperMoves.heatName,
            HelperMoves.arrivalName,
            HelperMoves.theDropName,
            HelperMoves.onceUponATimeInHollywoodName,
            HelperMoves.roomName,
            HelperMoves.whiplashName,
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listMove)
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