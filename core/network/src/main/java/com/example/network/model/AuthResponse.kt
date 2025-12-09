package com.example.network.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("oauth")
    val oauth: OAuth
)

data class OAuth(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String
)
