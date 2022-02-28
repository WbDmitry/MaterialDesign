package com.example.materialdesign.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityApiBottomBinding
import com.example.materialdesign.databinding.ActivityMainBinding
import com.example.materialdesign.ui.main.MainFragment
import com.example.materialdesign.ui.settings.SettingsFragment
import com.example.materialdesign.ui.viewpager.EarthFragment
import com.example.materialdesign.ui.viewpager.MarsFragment
import com.example.materialdesign.ui.viewpager.SystemFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(App.APP_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences?.let {
            val themeId = it.getInt(App.PREF_THEME_KEY, R.style.MaterialDesignRed)
            setTheme(themeId)
        }
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    MainFragment()
                ).commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_image_day -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment()).commit()
                    true
                }

                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EarthFragment()).commit()
                    true
                }

                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsFragment()).commit()
                    true
                }

                R.id.bottom_view_system -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SystemFragment()).commit()
                    true
                }

                R.id.bottom_view_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SettingsFragment()).commit()
                    true
                }

                else -> true
            }
        }
    }
}