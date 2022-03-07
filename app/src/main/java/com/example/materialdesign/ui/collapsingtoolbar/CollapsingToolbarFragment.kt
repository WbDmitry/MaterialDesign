package com.example.materialdesign.ui.collapsingtoolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentCollapsingToolbarBinding

class CollapsingToolbarFragment : Fragment() {
    private lateinit var binding: FragmentCollapsingToolbarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollapsingToolbarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBehavior = ButtonBehavior(requireContext())
        (binding.fab.layoutParams as CoordinatorLayout.LayoutParams).behavior = buttonBehavior
    }

    companion object {
        fun newInstance() = CollapsingToolbarFragment()
    }
}