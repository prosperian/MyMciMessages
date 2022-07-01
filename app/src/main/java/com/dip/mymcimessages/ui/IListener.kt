package com.dip.mymcimessages.ui

import com.dip.mymcimessages.models.Message

interface IListener {
    fun showListDeleteDialog()
    fun updateMessage(message:Message)
    fun removeMessage(message: Message)
    fun shareMessage(message: Message)
}