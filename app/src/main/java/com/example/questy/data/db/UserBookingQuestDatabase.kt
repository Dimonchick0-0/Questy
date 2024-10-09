package com.example.questy.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.questy.domain.model.Quest

@Entity(tableName = "users")
data class UserBookingQuestDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val numberPhone: Long,
    val data: Int,
    val questTitle: Quest
)