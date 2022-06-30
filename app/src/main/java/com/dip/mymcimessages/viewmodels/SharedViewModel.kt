package com.dip.mymcimessages.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    val messageCount = MutableLiveData<Int>()

    fun sendMessage(count: Int) {
        messageCount.postValue(count)
    }
}