package com.dip.mymcimessages.api

import com.dip.mymcimessages.models.Message
import com.dip.mymcimessages.utils.Utils
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Utils.KEY)
    suspend fun getMessages(): Response<ApiResponse<MutableList<Message>>>

}