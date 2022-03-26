package com.example.materialdesign.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentNotesBinding
import com.example.materialdesign.model.NotesEntity

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding: FragmentNotesBinding
        get() = _binding!!

    private val notesList = arrayListOf(
        NotesEntity("Title 1", "Description 1"),
        NotesEntity("Title 2", "Description 2"),
        NotesEntity("Title 3", "Description 3"),
        NotesEntity("Title 4", "Description 4"),
        NotesEntity("Title 5", "Description 5"),
        NotesEntity("Title 6", "Description 6"),
        NotesEntity("Title 7", "Description 7"),
        NotesEntity("Title 8", "Description 8"),
        NotesEntity("Title 9", "Description 9"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesRecyclerView.adapter = NotesAdapter({
            Toast.makeText(context, it.note_title + "\n" + it.note_description, Toast.LENGTH_SHORT)
                .show()
        }, notesList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}