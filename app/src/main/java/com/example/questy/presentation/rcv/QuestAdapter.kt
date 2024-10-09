package com.example.questy.presentation.rcv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.questy.R
import com.example.questy.databinding.QuestItemBinding
import com.example.questy.domain.model.Quest
import com.example.questy.presentation.rcv.diffutill.DiffUtillQuestItemCallback

class QuestAdapter :
    ListAdapter<Quest, QuestAdapter.QuestViewHolder>(DiffUtillQuestItemCallback()) {
    var onQuestItemClickListener: ((Quest) -> Unit)? = null

    class QuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = QuestItemBinding.bind(view)
        fun setQuestItem(quest: Quest) = with(binding) {
            quest.image?.let { imQuest.setImageResource(it) }
            tvQuestTitle.text = quest.title
            tvDescr.text = quest.description
            btnChooseQuest.text = quest.btnQuest
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.quest_item,
            parent,
            false
        )
        return QuestViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        val item = getItem(position)
        holder.setQuestItem(item)
        holder.binding.btnChooseQuest.setOnClickListener {
            onQuestItemClickListener?.invoke(item)
        }
    }
}