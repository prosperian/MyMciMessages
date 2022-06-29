package com.dip.mymcimessages.api

data class ApiResponse<T>(
    val messages: T
)