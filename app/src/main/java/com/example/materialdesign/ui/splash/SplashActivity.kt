package com.example.materialdesign.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivitySplashBinding
import com.example.materialdesign.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.animate().rotationBy(720f).setInterpolator(LinearInterpolator()).duration = 10_000L

        Handler(Looper.myLooper()!!).postDelayed({
          startActivity(Intent(this, MainActivity::class.java))
            finish()
        },3_000L)
    }
}