package com.example.materialdesign.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.databinding.FragmentNotesBinding
import com.example.materialdesign.model.ITEM_CLOSE
import com.example.materialdesign.model.NotesEntity

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding: FragmentNotesBinding
        get() = _binding!!

    private lateinit var adapter: NotesAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    private val notesList = arrayListOf(
        Pair(ITEM_CLOSE, NotesEntity("Title 1", "Description 1")),
        Pair(ITEM_CLOSE, NotesEntity("Title 2", "Description 2")),
        Pair(ITEM_CLOSE, NotesEntity("Title 3", "Description 3")),
        Pair(ITEM_CLOSE, NotesEntity("Title 4", "Description 4")),
        Pair(ITEM_CLOSE, NotesEntity("Title 5", "Description 5")),
        Pair(ITEM_CLOSE, NotesEntity("Title 6", "Description 6")),
        Pair(ITEM_CLOSE, NotesEntity("Title 7", "Description 7")),
        Pair(ITEM_CLOSE, NotesEntity("Title 8", "Description 8")),
        Pair(ITEM_CLOSE, NotesEntity("Title 9", "Description 9"))
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


        adapter = NotesAdapter({
            Toast.makeText(context, it.note_title + "\n" + it.note_description, Toast.LENGTH_SHORT)
                .show()
        }, notesList, {
            itemTouchHelper.startDrag(it)
        })

        binding.notesRecyclerView.adapter = adapter
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallBack(adapter))
        itemTouchHelper.attachToRecyclerView(binding.notesRecyclerView)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }

    class ItemTouchHelperCallBack(private val adapter: NotesAdapter) : ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipe = ItemTouchHelper.START
            return makeMovementFlags(drag, swipe)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                (viewHolder as ItemTouchHelperViewAdapter).onItemSelector()
            }
            super.onSelectedChanged(viewHolder, actionState)
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            (viewHolder as ItemTouchHelperViewAdapter).onItemClear()
            super.clearView(recyclerView, viewHolder)
        }
    }
}