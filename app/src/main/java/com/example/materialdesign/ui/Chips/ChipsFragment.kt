package com.example.materialdesign.ui.Chips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentChipsBinding
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout

class ChipsFragment : Fragment() {

    private var _binding: FragmentChipsBinding? = null
    val binding: FragmentChipsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.chipGroup.findViewById<Chip>(checkedId)?.let { it ->
                Toast.makeText(requireContext(), "${it.text} ${checkedId}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.chipEntry.setOnCloseIconClickListener {
            Toast.makeText(requireContext(), "chipEntry close", Toast.LENGTH_SHORT).show()
        }

        binding.tabs.getTabAt(0)!!.text = "Сегодня"
        binding.tabs.getTabAt(0)!!.icon = resources.getDrawable(R.drawable.ic_baseline_search_24)
        binding.tabs.getTabAt(1)!!.text = "Вчера"
        binding.tabs.getTabAt(1)!!.icon = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
        binding.tabs.getTabAt(2)!!.text = "Позавчера"
        binding.tabs.getTabAt(2)!!.icon = resources.getDrawable(R.drawable.ic_baseline_add_24)
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChipsFragment()
    }
}