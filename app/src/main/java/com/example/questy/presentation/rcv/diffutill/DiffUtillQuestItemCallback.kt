package com.example.questy.presentation.rcv.diffutill

import androidx.recyclerview.widget.DiffUtil
import com.example.questy.domain.model.Quest

class DiffUtillQuestItemCallback: DiffUtil.ItemCallback<Quest>() {
    override fun areItemsTheSame(oldItem: Quest, newItem: Quest): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Quest, newItem: Quest): Boolean {
        return oldItem == newItem
    }
}