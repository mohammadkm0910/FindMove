package com.mohammad.kk.findmove.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class FontTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context?) : this(context!!,null){
        initFont()
    }
    constructor(context: Context?, attrs: AttributeSet?) : this(context!!, attrs,0){
        initFont()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        initFont()
    }
    private fun initFont(){
        val typeFace = Typeface.createFromAsset(context.assets,"fonts/Vazir.ttf")
        typeface = typeFace
    }
}