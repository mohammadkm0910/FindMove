package com.mohammad.kk.findmove.util

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

class RootSnackBar(private var activity: Activity, private var message:String,private var counter:Int = 0) {
    fun show(){
        val rootView:View= activity.window.decorView.findViewById(android.R.id.content)
        val snackBar = Snackbar.make(rootView,message,Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(randomColor(counter))
        snackBar.show()
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