package com.example.materialdesign.ui.text

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    private lateinit var binding: FragmentTextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.font1Text1TextView.typeface = Typeface.createFromAsset(
            requireContext().assets,
            "fontheader/AndroidInsomniaRegular-RLxW.ttf"
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.font2Text1TextView.typeface =
                resources.getFont(R.font.mightsilly_bwny_b)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TextFragment()
    }
}