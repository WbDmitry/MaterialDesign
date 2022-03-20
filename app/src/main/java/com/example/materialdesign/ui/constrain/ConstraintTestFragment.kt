package com.example.materialdesign.ui.constrain

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.materialdesign.databinding.FragmentConstraintTestBinding

class ConstraintTestFragment : Fragment() {
    private lateinit var binding: FragmentConstraintTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConstraintTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMinus1.setOnClickListener {
            setTextInTextViewPlus(ONE, "")
        }

        binding.buttonMinus2.setOnClickListener {
            setTextInTextViewPlus(TWO, "")
        }

        binding.buttonMinus3.setOnClickListener {
            setTextInTextViewPlus(THREE, "")
        }

        binding.buttonPlus1.setOnClickListener {
            setTextInTextViewPlus(ONE, PLUS)
        }

        binding.buttonPlus2.setOnClickListener {
            setTextInTextViewPlus(TWO, PLUS)
        }

        binding.buttonPlus3.setOnClickListener {
            setTextInTextViewPlus(THREE, PLUS)
        }
    }

    private fun setTextInTextViewPlus(i: Int, operation: String) {
        val sum = binding.result.text.toString().toInt()
        val total: Int

        if (operation == PLUS) {
            total = sum + i

            animationOne()
            animationThree(true)
            animationFour(0.3f)
            binding.groupPlusButton.visibility = View.INVISIBLE
            binding.groupMinusButton.visibility = View.VISIBLE
        } else {
            total = sum - i

            animationTwo()
            animationThree(false)
            animationFour(1f)
            binding.groupPlusButton.visibility = View.VISIBLE
            binding.groupMinusButton.visibility = View.INVISIBLE
        }

        binding.result.text = total.toString()
    }

    private fun animationOne() {
        TransitionManager.beginDelayedTransition(
            binding.testContainerAnimation,
            Slide(Gravity.START)
        )
    }

    private fun animationTwo() {
        val slide = Slide(Gravity.END)
        val fade = Fade()

        slide.duration = DURATION_SLIDE
        fade.duration = DURATION_FADE

        val animationSet = TransitionSet()
            .addTransition(slide)
            .addTransition(fade)
        TransitionManager.beginDelayedTransition(
            binding.testContainerAnimation,
            animationSet
        )
    }

    private fun animationThree(b: Boolean) {
        if (b) {
            ObjectAnimator.ofFloat(binding.result, View.SCALE_X, 1f, 2f).setDuration(
                DURATION_ROTATE_TEXT
            )
                .start()
            ObjectAnimator.ofFloat(binding.result, View.SCALE_Y, 1f, 2f).setDuration(
                DURATION_ROTATE_TEXT
            )
                .start()
            ObjectAnimator.ofFloat(binding.result, View.ROTATION, 0f, 360f).setDuration(
                DURATION_ROTATE_TEXT
            )
                .start()
        } else {
            ObjectAnimator.ofFloat(binding.result, View.SCALE_X, 2f, 1f).setDuration(
                DURATION_ROTATE_TEXT
            )
                .start()
            ObjectAnimator.ofFloat(binding.result, View.SCALE_Y, 2f, 1f).setDuration(
                DURATION_ROTATE_TEXT
            )
                .start()
            ObjectAnimator.ofFloat(binding.result, View.ROTATION, 360f, 0f).setDuration(
                DURATION_ROTATE_TEXT
            )
                .start()
        }
    }

    private fun animationFour(fl: Float) {
        binding.headerTestContainer.animate()
            .alpha(fl).duration = DURATION_ROTATE_TEXT
    }

    companion object {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
        const val PLUS = "+"
        const val DURATION_SLIDE = 1_000L
        const val DURATION_FADE = 1_000L
        const val DURATION_ROTATE_TEXT = 1_000L

        fun newInstance() = ConstraintTestFragment()

    }
}