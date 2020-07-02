package com.kana_tutor.example.searchwindow


import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.search_window.view.*
import kotlinx.android.synthetic.main.search_window.view.search_ET
import kotlinx.android.synthetic.main.search_window.view.search_clear_BTN

import com.kana_tutor.example.searchwindow.R

interface SearchOnClick {
    fun searchOnClick(view : View, item : String)
}
class SearchWindow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle : Int = 0
) : RelativeLayout(context, attrs, defStyle) {
    private var searchOnClickListener : SearchOnClick? = null

    private fun hideKeyboard() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE)
            as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    init {
        Log.d("SearchWindow", "$context:$attrs")
        View.inflate(context, R.layout.search_window, this)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(
                    it, R.styleable.search_window_attrs, 0, 0)
            val hint = resources.getText(typedArray.getResourceId(
                R.styleable.search_window_attrs_hint, R.string.search))
            search_ET.hint = hint
            val colorId = ContextCompat.getColor(context, typedArray.getResourceId(
                R.styleable.search_window_attrs_textColor, android.R.color.tab_indicator_text))
            search_ET.setTextColor(colorId)

            typedArray.recycle()
        }
        search_ET.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val textIn = v.text.toString()
                    searchOnClickListener?.searchOnClick(this, textIn)
                    hideKeyboard()
                    Log.d("SearchWindow", "text in:${v.text.toString()}")
                    true
                }
                else -> {
                    false
                }
            }
        }
        search_clear_BTN.setOnClickListener(
            {search_ET.setText("")}
        )
        search_search_BTN.setOnClickListener(fun (_) {
            val textIn = search_ET.text.toString();
            if (text.length > 0) {
                hideKeyboard()
                searchOnClickListener?.searchOnClick(this, textIn)
            }
        })
    }
    var search_btn_visibility : Int
        get() = search_search_BTN.visibility
        set(vis:Int) { search_search_BTN.visibility = vis }
    var text : String
        get() : String  = search_ET.text.toString()
        set(str:String) { search_ET.setText(str) }
    fun setSearchOnClick(listener: SearchOnClick) {
        searchOnClickListener = listener
    }
}
