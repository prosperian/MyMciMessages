package com.dip.mymcimessages.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dip.mymcimessages.models.Message

@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}