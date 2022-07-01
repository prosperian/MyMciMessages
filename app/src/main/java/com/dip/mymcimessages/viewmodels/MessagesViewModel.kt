package com.dip.mymcimessages.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.mymcimessages.api.ApiResponse
import com.dip.mymcimessages.api.Resource
import com.dip.mymcimessages.api.ResponseHandler
import com.dip.mymcimessages.models.Message
import com.dip.mymcimessages.modules.MainRepositoryModule
import com.dip.mymcimessages.repositories.DatabaseRepository
import com.dip.mymcimessages.repositories.NetworkRepository
import com.dip.mymcimessages.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
) :
    ViewModel() {

    private val _messageList: SingleLiveEvent<Resource<ApiResponse<MutableList<Message>>>> =
        SingleLiveEvent()
    val messageList get() = _messageList

    private val _bookmarkedList: MutableLiveData<MutableList<Message>> =
        MutableLiveData()
    val bookmarkedList get() = _bookmarkedList

    private val _messagesInserted: MutableLiveData<MutableList<Message>> = MutableLiveData()
    val messagesInserted get() = _messagesInserted

    fun getOnlineMessages() = viewModelScope.launch(Dispatchers.IO) {
        _messageList.postValue(Resource.Loading())
        val response = networkRepository.getMessages()
        val bookmarkMessages = databaseRepository.getBookmarkedMessages()
        response.body()?.messages?.forEach { message ->
            val msg = bookmarkMessages.find { it.id == message.id }
            if (msg != null) {
                message.bookmarked = true
            }
        }
        _messageList.postValue(ResponseHandler.handleResponse(response))
    }

    fun sortList(list: MutableList<Message>?): MutableList<Message>? {
        return list?.sortedWith(compareBy({ !it.unread }, { it.id })) as MutableList<Message>?
    }

    fun getBookmarkedMessages() = viewModelScope.launch(Dispatchers.IO) {
        _bookmarkedList.postValue(databaseRepository.getBookmarkedMessages())
    }

    fun getAllMessages() = viewModelScope.launch(Dispatchers.IO) {
        messagesInserted.postValue(databaseRepository.getAllMessages())
    }

    fun insertAllMessages(messages: MutableList<Message>) = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.insertAllMessages(messages)
        getAllMessages()
    }

    fun updateMessage(message: Message) = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.updateMessage(message)
    }

    fun deleteMessage(message: Message) = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.deleteMessage(message)
    }


}