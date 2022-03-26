package com.example.materialdesign.ui.notes

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewAdapter {
    fun onItemSelector()
    fun onItemClear()
}