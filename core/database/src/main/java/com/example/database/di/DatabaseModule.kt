package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.VeroDigitalDatabase
import com.example.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): VeroDigitalDatabase =
        Room.databaseBuilder(
            context,
            VeroDigitalDatabase::class.java,
            "vero_digital_task.db"
        ).build()

    @Provides
    fun provideTaskDao(db: VeroDigitalDatabase): TaskDao = db.taskDao()
}