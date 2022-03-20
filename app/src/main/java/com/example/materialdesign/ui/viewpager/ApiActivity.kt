package com.example.materialdesign.ui.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityApiBinding
import com.google.android.material.tabs.TabLayoutMediator

class ApiActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        val tabTitles = arrayOf("Earth", "Mars", "System")

        TabLayoutMediator(
            binding.tabLayoutPlanet,
            binding.viewPager
        ) { tab, position ->
            tab.text = tabTitles[position]
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_earth)
                1 -> tab.setIcon(R.drawable.ic_mars)
                2 -> tab.setIcon(R.drawable.ic_system)
            }
        }.attach()
    }
}