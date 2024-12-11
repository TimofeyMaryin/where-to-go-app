package com.where.to.go.internet.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class AuthRequestModel(
    val role: Int,
    val email: String?,
    val phone: String?,
    val password: String,
)

@Serializable
data class AuthResponseModel(
    val token: String // user
)