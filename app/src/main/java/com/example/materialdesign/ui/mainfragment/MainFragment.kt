package com.example.materialdesign.ui.mainfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesign.AppState
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.sendRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse(
                        "https://en.wikipedia.org/wiki/" +
                                binding.inputEditText.text.toString()
                    )
            })
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }
                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("mylogs", "slideOffset $slideOffset")
            }
        })

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {

            }
            is AppState.Loading -> {

            }
            is AppState.Success -> {
                binding.imageView.load(appState.pdoServerResponse.url) {
                    placeholder(R.drawable.ic_launcher_foreground)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_settings -> {
                showToast("Click settings")
            }

            R.id.app_bar_fav -> {
                showToast("Click fav")
            }

            android.R.id.home -> {
                showToast("Click hamburger menu")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast (text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}