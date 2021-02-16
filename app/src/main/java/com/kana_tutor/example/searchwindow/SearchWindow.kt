package com.kana_tutor.example.searchwindow
/*
 *  Copyright (C) 2021 kana-tutor.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.kana_tutor.example.searchwindow.databinding.SearchWindowBinding

interface SearchOnClick {
    fun searchOnClick(view : View, textIn : String)
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

    val binding:SearchWindowBinding
    val searchET:EditText
    val searchClearBTN:ImageButton
    val searchSearchBTN:ImageButton

    init {
        Log.d("SearchWindow", "$context:$attrs")
        View.inflate(context, R.layout.search_window, this)
        binding = SearchWindowBinding.inflate(
            LayoutInflater.from(context)
        )
        addView(binding.root)
        searchET = binding.searchET
        searchClearBTN = binding.searchClearBTN
        searchSearchBTN = binding.searchSearchBTN

        attrs?.let {
            with(searchET) {
                val typedArray =
                    context.obtainStyledAttributes(
                        it, R.styleable.SearchWindow, 0, 0
                    )
                hint = resources.getText(
                    typedArray.getResourceId(
                        R.styleable.SearchWindow_hint, R.string.search
                    )
                )
                val colorId = ContextCompat.getColor(
                    context, typedArray.getResourceId(
                        R.styleable.SearchWindow_textColor,
                        android.R.color.tab_indicator_text
                    )
                )
                setTextColor(colorId)
                val textSizePixels = typedArray.getDimensionPixelSize(
                    R.styleable.SearchWindow_textSize,
                    (searchET.textSize + 0.5).toInt()
                )
                setTextSize(COMPLEX_UNIT_PX, textSizePixels.toFloat())
                val ems = typedArray.getInt(
                    R.styleable.SearchWindow_ems, -1
                )
                if (ems > 0)
                    setEms(ems)
                typedArray.recycle()
            }
        }
        searchET.setOnEditorActionListener { v, actionId, _ ->
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
        searchClearBTN.setOnClickListener(
            {searchET.setText("")}
        )
        searchSearchBTN.setOnClickListener(fun (_) {
            val textIn = searchET.text.toString()
            if (text.length > 0) {
                hideKeyboard()
                searchOnClickListener?.searchOnClick(this, textIn)
            }
        })
    }
    var search_btn_visibility : Int
        get() = searchClearBTN.visibility
        set(vis) { searchSearchBTN.visibility = vis }
    var text : String
        get() : String  = searchET.text.toString()
        set(str) { searchET.setText(str) }
    fun setSearchOnClick(listener: SearchOnClick) {
        searchOnClickListener = listener
    }
}
