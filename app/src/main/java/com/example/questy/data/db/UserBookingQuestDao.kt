package com.example.questy.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.questy.domain.model.User

@Dao
interface UserBookingQuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookingQuest(userBookingQuestDatabase: UserBookingQuestDatabase)

    @Query("SELECT * FROM users")
    fun getUser(): LiveData<List<UserBookingQuestDatabase>>

}