package com.kana_tutor.example.searchwindow
/*
 *  Copyright (C) 2020 kana-tutor.com
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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchOnClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window_1.setSearchOnClick(this)
        window_2.setSearchOnClick(this)
    }

    fun showHide(view: View) {
        if (view == show_hide_button) {
            window_1.visibility =
                if (window_1.visibility == View.GONE) View.VISIBLE
                else View.GONE
        }
        else if (view == show_hide_search_button) {
            window_1.search_btn_visibility =
                if (window_1.search_btn_visibility == View.GONE) View.VISIBLE
                else View.GONE
        }
    }

    override fun searchOnClick(view: View, textIn: String) {
        val viewName = when (view) {
            window_1 -> "window_1"
            window_2 -> "window_2"
            else -> "unknown"
        }
        test_text_view.setText("{$viewName:$textIn}")
        Log.d("searchOnClick", "{$viewName:$textIn}")
    }
}
