package com.example.questy.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.questy.R
import com.example.questy.data.db.AppDatabase
import com.example.questy.data.db.UserBookingQuestDao
import com.example.questy.data.mapper.QuestListMapper
import com.example.questy.domain.model.Quest
import com.example.questy.domain.model.User
import com.example.questy.domain.repository.QuestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuesRepositoryImpl(
    application: Application
) : QuestRepository {
    private val initQuestList = Quests()
    private val questListLD = MutableLiveData<List<Quest>>()
    private var questList = mutableListOf<Quest>()
    private val userDao = AppDatabase.getInstance(application).userBookingQuest()
    private val mapper = QuestListMapper()

    init {
        setQuestList(initQuestList.questList())
    }

    override suspend fun getUser(): LiveData<List<User>> = MediatorLiveData<List<User>>().apply {
        addSource(userDao.getUser()) {
            mapper.mapListDbModelToListEntiy(it)
        }
    }

    override suspend fun bookingQuest(user: User) {
        userDao.insertBookingQuest(mapper.mapEntityToDbModel(user))
    }

    override fun getQuestList(): LiveData<List<Quest>> {
        return questListLD
    }

    override fun setQuestList(list: MutableList<Quest>) {
        questList = list
        updateList()
    }

    override fun getQuestId(questItemId: Int): Quest? {
        questListLD.value?.let { questItem ->
            return questItem.last{it.id == questItemId}
        }
        return null
    }

    private fun updateList() {
        questListLD.value = questList.toList()
    }
}