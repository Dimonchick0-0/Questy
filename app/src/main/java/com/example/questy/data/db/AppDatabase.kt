package com.example.questy.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UserBookingQuestDatabase::class], version = 3, exportSchema = false)
@TypeConverters(QuestConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userBookingQuest(): UserBookingQuestDao
    companion object {

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "quest.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}