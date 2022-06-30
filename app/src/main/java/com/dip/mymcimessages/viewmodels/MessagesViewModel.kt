package com.dip.mymcimessages.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.mymcimessages.api.ApiResponse
import com.dip.mymcimessages.api.Resource
import com.dip.mymcimessages.api.ResponseHandler
import com.dip.mymcimessages.models.Message
import com.dip.mymcimessages.modules.MainRepositoryModule
import com.dip.mymcimessages.repositories.NetworkRepository
import com.dip.mymcimessages.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {

    private val _messageList: MutableLiveData<Resource<ApiResponse<MutableList<Message>>>> =
        MutableLiveData()
    val messageList get() = _messageList

    fun getMessages() = viewModelScope.launch {
        _messageList.postValue(Resource.Loading())
        val response = networkRepository.getMessages()
        _messageList.postValue(ResponseHandler.handleResponse(response))
    }

}