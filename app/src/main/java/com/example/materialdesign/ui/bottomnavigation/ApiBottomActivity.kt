package com.example.materialdesign.ui.bottomnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityApiBottomBinding
import com.example.materialdesign.ui.viewpager.EarthFragment
import com.example.materialdesign.ui.viewpager.MarsFragment
import com.example.materialdesign.ui.viewpager.SystemFragment

class ApiBottomActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBottomBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
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

                else -> true
            }
        }

//        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_mars

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        badge.number = 10000
        badge.maxCharacterCount = 6
    }
}