package com.mohammad.kk.findmove.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

object Extension {
    private var urlLoad:String = ""
    fun String.toFaNumbers():String{
        var digits = this
        val faNumber = arrayOf(
            arrayOf("0", "۰"),
            arrayOf("1", "۱"),
            arrayOf("2", "۲"),
            arrayOf("3", "۳"),
            arrayOf("4", "۴"),
            arrayOf("5", "۵"),
            arrayOf("6", "۶"),
            arrayOf("7", "۷"),
            arrayOf("8", "۸"),
            arrayOf("9", "۹")
        )
        for (i in faNumber){
            digits = digits.replace(i[0], i[1])
        }
        return digits
    }
    fun String.toEnNumbers():String{
        var digits = this
        val faNumber = arrayOf(
            arrayOf("۰", "0"),
            arrayOf("۱", "1"),
            arrayOf("۲", "2"),
            arrayOf("۳", "3"),
            arrayOf("۴", "4"),
            arrayOf("۵", "5"),
            arrayOf("۶", "6"),
            arrayOf("۷", "7"),
            arrayOf("۸", "8"),
            arrayOf("۹", "9")
        )
        for (i in faNumber){
            digits = digits.replace(i[0], i[1])
        }
        return digits
    }
    fun String.eval():Double{
        var result = 0.0
        var wording = this
        wording = wording.replace("×", "*")
        wording = wording.replace("÷", "/")
        wording = wording.replace("%", "/100")
        wording = wording.replace("√", "sqrt")
        try {
            if (wording.isNotEmpty()){
                val expression = ExpressionBuilder(wording).build()
                result = expression.evaluate()
            }
        } catch (e: Exception){
            e.printStackTrace()
            result = 0.0
        }
        return result
    }
    fun String.toSeparatorComma():String{
        if (this.isNotEmpty()){
            val lst = StringTokenizer(this, ".")
            var str1 = this
            var str2 = ""
            if (lst.countTokens() > 1) {
                str1 = lst.nextToken()
                str2 = lst.nextToken()
            }
            var str3 = ""
            var i = 0
            var j = -1 + str1.length
            if (str1[-1 + str1.length] == '.') {
                j--
                str3 = "."
            }
            var k = j
            while (true) {
                if (k < 0) {
                    if (str2.isNotEmpty()) str3 = "$str3.$str2"
                    return str3
                }
                if (i == 3) {
                    str3 = ",$str3"
                    i = 0
                }
                str3 = str1[k].toString() + str3
                i++
                k--
            }
        } else {
            return ""
        }
    }
    fun ImageView.loadUrl(url: Any){
        when (url) {
            is String -> Picasso.get().load(url).into(this)
            is Int -> Picasso.get().load(url).into(this)
            else -> Log.d("tag","Invalid address")
        }
        urlLoad = url.toString()
        this.scaleType = ImageView.ScaleType.CENTER_CROP
    }
    fun ImageView.getUrl():String{
        return if (urlLoad.isNotEmpty()) urlLoad else ""
    }
    @Suppress("DEPRECATION")
    fun isInternet(context: Context):Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }
}