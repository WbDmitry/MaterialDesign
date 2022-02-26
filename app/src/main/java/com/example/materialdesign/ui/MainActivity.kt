package com.example.materialdesign.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(App.APP_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences?.let {
            val themeId = it.getInt(App.PREF_THEME_KEY, R.style.MaterialDesignRed)
            setTheme(themeId)
        }

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    MainFragment.newInstance()
                ).commit()
        }
    }
}