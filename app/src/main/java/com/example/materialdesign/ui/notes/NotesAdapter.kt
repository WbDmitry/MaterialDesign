package com.example.materialdesign.ui.notes

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.materialdesign.databinding.FragmentNotesItemListBinding
import com.example.materialdesign.model.NotesEntity

class NotesAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var notesData: MutableList<Pair<Boolean, NotesEntity>>,
    private val onStartDragListener: OnStartDragListener
) : RecyclerView.Adapter<NotesAdapter.NotesHolder>(), ItemTouchHelperAdapter {


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
        ViewHolder(binding.root), ItemTouchHelperViewAdapter {
        @SuppressLint("ClickableViewAccessibility")
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

            dragNDropImageView.setOnTouchListener { view, motionEvent ->
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    onStartDragListener.onStartDrag(this@NotesHolder)
                }
                false
            }
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
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

        override fun onItemSelector() {
            itemView.setBackgroundColor(Color.GRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)

        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        notesData.removeAt(fromPosition).apply {
            notesData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        notesData.removeAt(position)
        notifyItemRemoved(position)
    }
}