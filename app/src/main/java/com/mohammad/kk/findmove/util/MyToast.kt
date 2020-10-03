package com.mohammad.kk.findmove.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class MyToast(private var context: Context, private var message:String) {
    @Suppress("DEPRECATION")
    fun show(){
        val random = Random.nextInt(6);
        val toast = Toast.makeText(context,message,Toast.LENGTH_LONG)
        val view: View? = toast.view
        view!!.setBackgroundColor(randomColor(random))
        view.setPadding(8,8,8,8)
        toast.show()
    }
    private fun randomColor(color:Int) = when(color){
        0 -> Color.rgb(41,121,255)
        1 -> Color.rgb(245,0,87)
        2 -> Color.rgb(255,61,0)
        3 -> Color.rgb(213,0,249)
        4 -> Color.rgb(0,230,118)
        5 -> Color.rgb(255,171,0)
        else -> Color.rgb(33,33,33)
    }
}