package com.dip.mymcimessages.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val image: String?,
    val unread: Boolean,
    var bookmarked: Boolean = false,
)
