package com.example.network.api

import com.example.network.model.TaskDto
import retrofit2.http.GET
import retrofit2.http.Header

interface TasksApi {

    @GET("index.php/v1/tasks/select")
    suspend fun getTasks(
        @Header("Authorization") authHeader: String
    ): List<TaskDto>
}
