package com.dip.mymcimessages.repositories

import androidx.annotation.WorkerThread
import com.dip.mymcimessages.db.MessageDao
import com.dip.mymcimessages.models.Message
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class DatabaseRepository @Inject constructor(private val messageDao: MessageDao) {
    @WorkerThread
    suspend fun getBookmarkedMessages(): MutableList<Message> {
        return messageDao.getBookmarkedMessages()
    }

    @WorkerThread
    suspend fun deleteMessage(message: Message) {
        messageDao.deleteMessage(message)
    }

    @WorkerThread
    suspend fun getAllMessages(): MutableList<Message>  {
        return messageDao.getAllMessages()
    }

    @WorkerThread
    suspend fun insertAllMessages(messages: List<Message>) {
        messageDao.insertAllMessages(messages)
    }

    @WorkerThread
    suspend fun updateMessage(message: Message) {
        messageDao.updateMessage(message)
    }
}