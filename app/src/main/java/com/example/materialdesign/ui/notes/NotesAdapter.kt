package com.example.materialdesign.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.databinding.FragmentNotesItemListBinding
import com.example.materialdesign.model.NotesEntity

class NotesAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var notesData: List<NotesEntity>
) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private lateinit var binding: FragmentNotesItemListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        binding = FragmentNotesItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotesHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.bind(notesData[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = notesData.size

    inner class NotesHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(notes: NotesEntity) = with(binding) {
            noteTitleTextView.text = notes.note_title
            dragNDropImageView.setOnClickListener {
                onListItemClickListener.onItemClick(notes)
            }
        }
    }
}