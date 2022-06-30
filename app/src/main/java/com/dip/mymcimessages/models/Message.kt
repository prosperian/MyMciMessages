package com.dip.mymcimessages.models

data class Message(
    val id: String,
    val title: String,
    val description: String,
    val image: String?,
    val unread: Boolean,
    var bookmarked: Boolean = false,
)
