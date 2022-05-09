package com.example.materialdesign.ui.main

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.model.repository.AppState
import java.util.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendRequest(takeDate(0))

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse(
                        URL_WIKI +
                                binding.inputEditText.text.toString()
                    )
            })
        }
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.today ->{viewModel.sendRequest(takeDate(0))}
                R.id.minus1day ->{viewModel.sendRequest(takeDate(-1))}
                R.id.minus2day ->{viewModel.sendRequest(takeDate(-2))}
                R.id.minus3day ->{viewModel.sendRequest(takeDate(-3))}
                R.id.minus4day ->{viewModel.sendRequest(takeDate(-4))}
                R.id.minus5day ->{viewModel.sendRequest(takeDate(-5))}

            }
        }
    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
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
                binding.included.bottomSheetDescription.text =
                    appState.pdoServerResponse.explanation
            }
        }
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
        const val URL_WIKI = "https://en.wikipedia.org/wiki/"
    }
}