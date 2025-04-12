package com.where.to.go.core.data.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("role") val role: Int,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)