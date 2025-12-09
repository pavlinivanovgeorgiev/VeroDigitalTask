package com.example.tasks.data

import com.example.tasks.domain.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun observeTasks(): Flow<List<Task>>
    suspend fun refreshTasks()
}
