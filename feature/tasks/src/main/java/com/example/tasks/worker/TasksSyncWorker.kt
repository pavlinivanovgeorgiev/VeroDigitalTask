package com.example.tasks.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tasks.data.TasksRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class TasksSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: TasksRepository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            repository.refreshTasks()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}