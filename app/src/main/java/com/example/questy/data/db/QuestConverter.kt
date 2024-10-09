package com.example.questy.data.db

import androidx.room.TypeConverter
import com.example.questy.domain.model.Quest

class QuestConverter {
    @TypeConverter
    fun fromQuestItem(quest: Quest): String {
        return quest.title
    }
    @TypeConverter
    fun toQuestItem(title: String): Quest {
        return Quest(null, title)
    }
}