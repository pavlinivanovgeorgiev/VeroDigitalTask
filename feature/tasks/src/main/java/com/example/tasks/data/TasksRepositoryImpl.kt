package com.example.tasks.data

import com.example.database.dao.TaskDao
import com.example.database.entity.TaskEntity
import com.example.network.api.AuthApi
import com.example.network.model.LoginRequest
import com.example.network.model.TaskDto
import com.example.network.api.TasksApi
import com.example.tasks.domain.Task
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tasksApi: TasksApi,
    private val taskDao: TaskDao,
    private val gson: Gson
) : TasksRepository {

    override fun observeTasks(): Flow<List<Task>> =
        taskDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun refreshTasks() {
        val authResponse = authApi.login(
            LoginRequest(
                username = "365",
                password = "1"
            )
        )
        val token = authResponse.oauth.accessToken
        val tokenType = authResponse.oauth.tokenType
        val bearer = "$tokenType $token"

        val remoteTasks: List<TaskDto> = tasksApi.getTasks(bearer)

        val entities: List<TaskEntity> = remoteTasks.map { it.toEntity(gson) }

        taskDao.clear()
        taskDao.insertAll(entities)
    }
}
