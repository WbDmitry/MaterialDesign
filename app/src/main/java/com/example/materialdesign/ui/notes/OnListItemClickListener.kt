package com.example.materialdesign.ui.notes

import com.example.materialdesign.model.NotesEntity

fun interface OnListItemClickListener {
    fun onItemClick(notesEntity: NotesEntity)
}