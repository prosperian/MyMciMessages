package com.dip.mymcimessages.db

import androidx.room.*
import com.dip.mymcimessages.models.Message

@Dao
interface MessageDao {
    @Query("select * from message where bookmarked ")
    fun getBookmarkedMessages(): MutableList<Message>

    @Query("select * from message")
    fun getAllMessages(): MutableList<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMessages(messages: List<Message>)

    @Delete
    fun deleteMessage(message: Message)

    @Update
    fun updateMessage(message: Message)
}