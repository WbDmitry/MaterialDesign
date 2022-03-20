package com.example.materialdesign.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {
    private lateinit var binding: FragmentEarthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiKey: String = BuildConfig.NASA_API_KEY
        val url = IMAGE_URL + apiKey
        binding.imageView.load(url)
        binding.textTitlePlanet.text = TITLE_EARTH_MAP
    }

    companion object {
        fun newInstance() = EarthFragment()
        private const val TITLE_EARTH_MAP = "MAPS SITY"
        private const val IMAGE_URL =
            "https://api.nasa.gov/planetary/earth/imagery?lon=100.75&lat=1.5&date=2014-02-01&api_key="
    }
}