package com.example.verodigitaltask

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.tasks.worker.TasksSyncWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class VeroDigitalTaskApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        val request = PeriodicWorkRequestBuilder<TasksSyncWorker>(
            60, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "tasks_sync",
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }
}


