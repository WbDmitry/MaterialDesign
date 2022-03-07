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
            setTextInTextViewMinus(1)
        }

        binding.buttonMinus2.setOnClickListener {
            setTextInTextViewMinus(2)
        }
        binding.buttonMinus3.setOnClickListener {
            setTextInTextViewMinus(3)
        }

        binding.buttonPlus1.setOnClickListener {
            setTextInTextViewPlus(1)
        }
        binding.buttonPlus2.setOnClickListener {
            setTextInTextViewPlus(2)
        }
        binding.buttonPlus3.setOnClickListener {
            setTextInTextViewPlus(3)
        }
    }

    private fun setTextInTextViewPlus(i: Int) {
        val sum = binding.result.text.toString().toInt()
        val total = sum + i
        binding.result.text = total.toString()
        binding.groupPlusButton.visibility = View.INVISIBLE
        binding.groupMinusButton.visibility = View.VISIBLE
    }

    private fun setTextInTextViewMinus(i: Int) {
        val sum = binding.result.text.toString().toInt()
        val total = sum - i
        binding.result.text = total.toString()
        binding.groupPlusButton.visibility = View.VISIBLE
        binding.groupMinusButton.visibility = View.INVISIBLE
    }

    companion object {
        fun newInstance() = ConstraintTestFragment()
    }
}