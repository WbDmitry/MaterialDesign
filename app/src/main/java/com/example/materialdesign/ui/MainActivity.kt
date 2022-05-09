package com.example.materialdesign.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityMainBinding
import com.example.materialdesign.ui.collapsingtoolbar.CollapsingToolbarFragment
import com.example.materialdesign.ui.constrain.ConstraintTestFragment
import com.example.materialdesign.ui.main.MainFragment
import com.example.materialdesign.ui.settings.SettingsFragment
import com.example.materialdesign.ui.text.TextFragment
import com.example.materialdesign.ui.viewpager.EarthFragment

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
            openFragment(MainFragment())
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_image_day -> {
                    openFragment(MainFragment())
                    true
                }

                R.id.bottom_view_earth -> {
                    openFragment(EarthFragment())
                    true
                }

                R.id.bottom_view_constraint_layout -> {
                    openFragment(ConstraintTestFragment())
                    true
                }

                R.id.bottom_view_collapsing_toolbar -> {
                    openFragment(CollapsingToolbarFragment())
                    true
                }

                R.id.bottom_view_settings -> {
                    openFragment(SettingsFragment())
                    true
                }

                else -> true
            }
        }
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).addToBackStack("").commit()
    }
}