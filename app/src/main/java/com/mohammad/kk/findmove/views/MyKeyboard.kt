package com.mohammad.kk.findmove.views

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import com.mohammad.kk.findmove.R
import kotlinx.android.synthetic.main.my_keyboard.view.*

class MyKeyboard : LinearLayout, View.OnClickListener {
    private val keyValue:SparseArray<String> = SparseArray()
    private var  inputConnection: InputConnection? = null
    constructor(context: Context) : this(context,null){initKey(context)}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0){initKey(context)}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initKey(context)
    }
    private fun initKey(ctx:Context){
        val view = LayoutInflater.from(ctx).inflate(R.layout.my_keyboard,this,true)
        view.keyZero.setOnClickListener(this)
        view.keyOne.setOnClickListener(this)
        view.keyTwo.setOnClickListener(this)
        view.keyThree.setOnClickListener(this)
        view.keyFore.setOnClickListener(this)
        view.keyFive.setOnClickListener(this)
        view.keySix.setOnClickListener(this)
        view.keySeven.setOnClickListener(this)
        view.keyEight.setOnClickListener(this)
        view.keyNine.setOnClickListener(this)
        view.keyPlus.setOnClickListener(this)
        view.keyMinus.setOnClickListener(this)
        view.keyStar.setOnClickListener(this)
        view.keySlash.setOnClickListener(this)
        keyValue.put(R.id.keyZero,"0")
        keyValue.put(R.id.keyOne,"1")
        keyValue.put(R.id.keyTwo,"2")
        keyValue.put(R.id.keyThree,"3")
        keyValue.put(R.id.keyFore,"4")
        keyValue.put(R.id.keyFive,"5")
        keyValue.put(R.id.keySix,"6")
        keyValue.put(R.id.keySeven,"7")
        keyValue.put(R.id.keyEight,"8")
        keyValue.put(R.id.keyNine,"9")
        keyValue.put(R.id.keyPlus,"+")
        keyValue.put(R.id.keyMinus,"-")
        keyValue.put(R.id.keyStar,"*")
        keyValue.put(R.id.keySlash,"/")
    }
    override fun onClick(v: View?) {
        if (inputConnection == null) return
        val values = keyValue.get(v!!.id)
        inputConnection!!.commitText(values,1)
    }
    fun setInputConnection(ic:InputConnection) {inputConnection = ic}
}