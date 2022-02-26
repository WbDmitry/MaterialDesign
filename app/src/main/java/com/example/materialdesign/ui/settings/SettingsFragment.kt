package com.example.materialdesign.ui.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentSettingsBinding
import com.example.materialdesign.ui.App

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    val binding: FragmentSettingsBinding
        get() = _binding!!

    private var sharedPreferences: SharedPreferences? = null
    private var themeId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = activity?.getSharedPreferences(App.APP_PREF_NAME, Context.MODE_PRIVATE)
        themeId = sharedPreferences?.getInt(App.PREF_THEME_KEY, R.style.MaterialDesignRed)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.check(
            when (themeId) {
                R.style.MaterialDesignBlue -> R.id.theme_blue
                R.style.MaterialDesignGreen -> R.id.theme_green
                else -> R.id.theme_red
            }
        )
        binding.themeRed.setOnClickListener {
            changeTheme(1)
        }
        binding.themeBlue.setOnClickListener {
            changeTheme(2)
        }
        binding.themeGreen.setOnClickListener {
            changeTheme(3)
        }
    }

    private fun changeTheme(chekChipPosition: Int) {
        sharedPreferences?.edit()?.putInt(
            App.PREF_THEME_KEY,
            when (chekChipPosition) {
                2 -> R.style.MaterialDesignBlue
                3 -> R.style.MaterialDesignGreen
                else -> R.style.MaterialDesignRed
            }
        )?.apply()
        activity?.recreate()
    }

    private fun Fragment.toast(text: String?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}