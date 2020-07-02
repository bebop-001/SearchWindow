package com.kana_tutor.example.searchwindow
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
