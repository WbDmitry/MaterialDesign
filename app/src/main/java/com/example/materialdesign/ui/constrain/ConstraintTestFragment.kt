package com.example.materialdesign.ui.constrain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
                binding.groupPlusButton.visibility = View.INVISIBLE
                binding.groupMinusButton.visibility = View.VISIBLE
            } else {
                total = sum - i
                binding.groupPlusButton.visibility = View.VISIBLE
                binding.groupMinusButton.visibility = View.INVISIBLE
            }
        binding.result.text = total.toString()
    }

    companion object {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
        const val PLUS = "+"

        fun newInstance() = ConstraintTestFragment()

    }
}