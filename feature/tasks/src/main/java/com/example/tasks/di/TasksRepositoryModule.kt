package com.example.tasks.di

import com.example.tasks.data.TasksRepository
import com.example.tasks.data.TasksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TasksRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTasksRepository(
        impl: TasksRepositoryImpl
    ): TasksRepository
}
