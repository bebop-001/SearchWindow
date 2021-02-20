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
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kana_tutor.example.searchwindow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var window1: SearchWindow
    private lateinit var window2: SearchWindow
    private lateinit var showHideButton: Button
    private lateinit var testTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window1 = binding.window1
        window2 = binding.window2
        showHideButton = binding.showHideButton
        testTextView = binding.testTextView

        setContentView(binding.root)
        window1.setSearchOnClick(this::searchOnClick)
        window2.setSearchOnClick(this::searchOnClick)
    }

    fun showHide(view: View) {
        if (view == binding.showHideButton) {
            window1.visibility =
                if (window1.visibility == View.GONE) View.VISIBLE
                else View.GONE
        }
        else if (view == binding.showHideButton) {
            window1.search_btn_visibility =
                if (window1.search_btn_visibility == View.GONE) View.VISIBLE
                else View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    fun searchOnClick(view: View, textIn: String) {
        val viewName = when (view) {
            window1 -> "window1"
            window2 -> "window2"
            else -> "unknown"
        }
        testTextView.text = "{$viewName:$textIn}"
        Log.d("searchOnClick", "{$viewName:$textIn}")
    }
}
