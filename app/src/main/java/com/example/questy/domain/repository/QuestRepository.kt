package com.example.questy.domain.repository

import androidx.lifecycle.LiveData
import com.example.questy.domain.model.Quest
import com.example.questy.domain.model.User

interface QuestRepository {
    fun getQuestList(): LiveData<List<Quest>>
    fun setQuestList(list: MutableList<Quest>)
    fun getQuestId(questItemId: Int): Quest?
    suspend fun getUser(): LiveData<List<User>>
    suspend fun bookingQuest(user: User)
}