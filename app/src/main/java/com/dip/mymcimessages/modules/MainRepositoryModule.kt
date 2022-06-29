package com.dip.mymcimessages.modules

import com.dip.mymcimessages.api.ApiService
import com.dip.mymcimessages.repositories.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainRepositoryModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(apiService: ApiService): NetworkRepository {
        return NetworkRepository(apiService)
    }

}