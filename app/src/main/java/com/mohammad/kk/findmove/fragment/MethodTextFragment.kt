package com.mohammad.kk.findmove.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mohammad.kk.findmove.R
import com.mohammad.kk.findmove.util.Extension.eval
import com.mohammad.kk.findmove.util.Extension.toEnNumbers
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.Extension.toSeparatorComma
import com.mohammad.kk.findmove.views.MyKeyboard
import kotlinx.android.synthetic.main.extension_method_dialog.view.*

class MethodTextFragment: Fragment() {
    private lateinit var expressionEditText:EditText
    private lateinit var expressionTextView:TextView
    private lateinit var keyApp:MyKeyboard
    private lateinit var textMethodLayout:LinearLayout
    private lateinit var progressRefreshCenter:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.method_extension_text_fragment,container,false)
        getIds(view)
        setupKeyboard()
        expressionEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                expressionTextView.text = setupExpression(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        return view
    }
    private fun getIds(view: View) {
        expressionEditText = view.findViewById(R.id.expressionEditText)
        expressionTextView = view.findViewById(R.id.expressionTextView)
        keyApp = view.findViewById(R.id.keyApp)
        textMethodLayout = view.findViewById(R.id.textMethodLayout)
        progressRefreshCenter = view.findViewById(R.id.progressRefreshCenter)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.add(0,1,1,"تازه سازی").setIcon(R.drawable.ic_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.add(0,2,2,"منو").setIcon(R.drawable.ic_menu).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        super.onCreateOptionsMenu(menu, inflater)
    }
    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> {
                textMethodLayout.visibility = View.GONE
                progressRefreshCenter.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    textMethodLayout.visibility = View.VISIBLE
                    progressRefreshCenter.visibility = View.GONE
                    expressionEditText.clearFocus()
                    expressionEditText.text.clear()
                    expressionTextView.text = ""
                    TEXT_CONFIG = 1
                    expressionEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                    val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(expressionEditText.windowToken, 0)
                },1000)
            }
            2 -> {
                val initView = layoutInflater.inflate(R.layout.extension_method_dialog,null)
                val bottomSheetDialog = BottomSheetDialog(activity!!)
                bottomSheetDialog.setContentView(initView)
                initView.navExtensionDialog.setNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.separatorNumber -> {
                            TEXT_CONFIG = 1
                            expressionTextView.text = setupExpression(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        R.id.faNumber -> {
                            TEXT_CONFIG = 2
                            expressionTextView.text = setupExpression(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        R.id.enEnglish -> {
                            TEXT_CONFIG = 3
                            expressionTextView.text = setupExpression(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        R.id.calculatorExpression -> {
                            TEXT_CONFIG = 4
                            expressionTextView.text = setupExpression(expressionEditText.text.toString())
                            expressionEditText.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                        }
                    }
                    bottomSheetDialog.dismiss()

                    true
                }
                bottomSheetDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setupKeyboard() {
        val ic = expressionEditText.onCreateInputConnection(EditorInfo())
        keyApp.setInputConnection(ic)
    }
    private fun setupExpression(text:String):String = when(TEXT_CONFIG){
        1 -> text.toSeparatorComma()
        2 -> text.toFaNumbers()
        3 -> text.toEnNumbers()
        4 -> text.eval().toString()
        else -> text
    }
    companion object {
        private var TEXT_CONFIG = 1
    }

}