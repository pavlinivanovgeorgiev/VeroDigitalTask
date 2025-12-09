package com.example.network.api

import com.example.network.model.AuthResponse
import com.example.network.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @POST("index.php/login")
    @Headers(
        "Authorization: Basic QVBJX0V4cGxvcmVyOjEyMzQ1NmlzQUxhbWVQYXNz",
        "Content-Type: application/json"
    )
    suspend fun login(
        @Body body: LoginRequest
    ): AuthResponse
}
