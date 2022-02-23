package com.example.materialdesign.ui.mainfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesign.model.repository.AppState
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.ui.сhips.ChipsFragment
import com.example.materialdesign.ui.MainActivity
import com.example.materialdesign.ui.navfragment.BottomNavigationDrawerFragment
import com.google.android.material.bottomappbar.BottomAppBar
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
                        URL_WIKI +
                                binding.inputEditText.text.toString()
                    )
            })
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

//        bottomSheetBehavior.addBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                        toast("STATE_COLLAPSED")
//                    }
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                        toast("STATE_DRAGGING")
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                        toast("STATE_EXPANDED")
//                    }
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//                        toast("STATE_HALF_EXPANDED")
//                    }
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                        toast("STATE_HIDDEN")
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                        toast("STATE_SETTLING")
//                    }
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                Log.d(LOG_STRING, "slideOffset $slideOffset")
//            }
//        })

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageResource(R.drawable.ic_baseline_arrow_back_24)
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_empty)
            } else {
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_menu_24)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageResource(R.drawable.ic_baseline_add_24)
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                toast(appState.error.message)
            }
            is AppState.Loading -> {
                //показывается лоадер
            }
            is AppState.Success -> {
                binding.imageView.load(appState.pdoServerResponse.url) {
                    placeholder(R.drawable.ic_launcher_foreground)
                }
                binding.included.bottomSheetHeader.text = appState.pdoServerResponse.title
                binding.included.bottomSheetDescription.text = appState.pdoServerResponse.explanation
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_settings -> {
                toast(LOG_STRING)
            }

            R.id.app_bar_fav -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.container,
                        ChipsFragment.newInstance()
                    )
                    .addToBackStack("")
                    .commit()
            }

            android.R.id.home -> {
                BottomNavigationDrawerFragment()
                    .show(
                        requireActivity()
                            .supportFragmentManager,
                        LOG_STRING
                    )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    private fun Fragment.toast(text: String?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()

        var isMain = true

        const val URL_WIKI = "https://en.wikipedia.org/wiki/"
        const val LOG_STRING = "mylogs"
    }
}