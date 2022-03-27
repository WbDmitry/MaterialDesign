package com.example.materialdesign.ui.text

import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

        val text = "The foundational elements of print "
        val text2 =
            "based design typography, grids, space, scale, color, and use of imagery guide visual treatments."

        val spannableStringBuilder = SpannableStringBuilder(text)
        spannableStringBuilder.setSpan(
            StyleSpan(Typeface.BOLD_ITALIC),
            0,
            16,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val fontSizeInPx = 70
        spannableStringBuilder.setSpan(
            AbsoluteSizeSpan(fontSizeInPx),
            17,
            34,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.insert(text.length, text2)

        val colorText = ContextCompat.getColor(requireContext(), R.color.red_900)
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(colorText),
            text.length,
            text.length + 12,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val colorBg = ContextCompat.getColor(requireContext(), android.R.color.holo_blue_light)
        spannableStringBuilder.setSpan(
            BackgroundColorSpan(colorBg),
            text.length + 13,
            text.length + 45,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val blurRadius = 5f
        val blurMaskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID)
        spannableStringBuilder.setSpan(
            MaskFilterSpan(blurMaskFilter),
            text.length + 45,
            text.length + 60,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.setSpan(
            StrikethroughSpan(),
            text.length + 60,
            text.length + 83,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.setSpan(
            UnderlineSpan(),
            text.length + 84,
            spannableStringBuilder.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.spansTextView.text = spannableStringBuilder
    }

    companion object {
        @JvmStatic
        fun newInstance() = TextFragment()
    }
}