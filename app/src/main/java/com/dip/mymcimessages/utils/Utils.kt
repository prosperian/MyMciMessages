package com.dip.mymcimessages.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.dip.mymcimessages.models.Message

object Utils {

    const val BASE_URL = "https://run.mocky.io/v3/"
    const val KEY = "729e846c-80db-4c52-8765-9a762078bc82"

    const val AnimationDurationLong = 500L
    const val DATABASE_NAME = "bookmark_messages"

    fun shareMessage(message: Message, context: Context) {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message.description)
            putExtra(Intent.EXTRA_TITLE, message.title)
            if (message.image != null)
                data = Uri.parse(message.image)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        context.startActivity(share)
    }

}