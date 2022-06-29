package com.dip.mymcimessages.repositories

import androidx.annotation.WorkerThread
import com.dip.mymcimessages.api.ApiResponse
import com.dip.mymcimessages.api.ApiService
import com.dip.mymcimessages.models.Message
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class NetworkRepository @Inject constructor(private val apiService: ApiService) {

    @WorkerThread
    suspend fun getMessages(): Response<ApiResponse<MutableList<Message>>> {
        return apiService.getMessages()
    }

}