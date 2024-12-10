package com.where.to.go.internet.models

data class AuthRequestModel(
    val name: String?,
    val email: String?,
    val phone: String?,
    val password: String,
)

data class AuthResponseModel(
    val token: String
)