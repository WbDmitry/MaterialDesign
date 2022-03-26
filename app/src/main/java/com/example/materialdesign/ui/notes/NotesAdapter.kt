package com.example.materialdesign.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.materialdesign.databinding.FragmentNotesItemListBinding
import com.example.materialdesign.model.NotesEntity

class NotesAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var notesData: MutableList<Pair<Boolean, NotesEntity>>
) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NotesHolder(
        FragmentNotesItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.bind(notesData[position])
    }

    override fun getItemCount(): Int = notesData.size

    inner class NotesHolder(private val binding: FragmentNotesItemListBinding) :
        ViewHolder(binding.root) {
        fun bind(notes: Pair<Boolean, NotesEntity>) = with(binding) {

            noteTitleTextView.text = notes.second.note_title
            noteDescriptionTextView.text = notes.second.note_description

            dragNDropImageView.setOnClickListener {
                onListItemClickListener.onItemClick(notes.second)
            }

            upArrowImageView.setOnClickListener {
                moveUp()
            }

            downArrowImageView.setOnClickListener {
                moveDown()
            }

            noteTitleTextView.setOnClickListener {
                notesData[layoutPosition] = notesData[layoutPosition].let {
                    val currentState = if (it.first) !it.first else !it.first
                    Pair(currentState, it.second)
                }
                notifyItemChanged(layoutPosition)
            }
            noteDescriptionTextView.visibility =
                if (notes.first) View.GONE else View.VISIBLE
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                notesData.removeAt(currentPosition).apply {
                    notesData.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < notesData.size - 1 }?.also { currentPosition ->
                notesData.removeAt(currentPosition).apply {
                    notesData.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }
    }
}