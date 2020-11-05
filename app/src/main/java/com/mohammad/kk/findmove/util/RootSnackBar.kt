package com.mohammad.kk.findmove.util

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mohammad.kk.findmove.R
import kotlin.random.Random

class RootSnackBar() {
    fun show(activity: Activity,message:String,isBgRandom:Boolean = false){
        val rootView:View= activity.window.decorView.findViewById(android.R.id.content)
        val snackBar = Snackbar.make(rootView,message,Snackbar.LENGTH_LONG)
        if (!isBgRandom)
            snackBar.setBackgroundTint(ContextCompat.getColor(activity, R.color.blue500))
        else
            snackBar.setBackgroundTint(randomColor())
        snackBar.show()
    }
    private fun randomColor():Int {
        val random1 = Random.nextInt(50,200)
        val random2 = Random.nextInt(50,200)
        val random3 = Random.nextInt(50,200)
        return Color.rgb(random1,random2,random3)
    }
}