package com.dip.mymcimessages.modules

import android.content.Context
import androidx.room.Room
import com.dip.mymcimessages.db.AppDatabase
import com.dip.mymcimessages.db.MessageDao
import com.dip.mymcimessages.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Utils.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideMessageDao(appDatabase: AppDatabase): MessageDao {
        return appDatabase.messageDao()
    }

}