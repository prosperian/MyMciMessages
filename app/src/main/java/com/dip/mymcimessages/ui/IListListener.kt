package com.dip.mymcimessages.ui

import com.dip.mymcimessages.models.Message


interface IListListener : IListener {
    fun listChanged(size: Int)
}