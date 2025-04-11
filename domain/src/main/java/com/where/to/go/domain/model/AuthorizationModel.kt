package com.where.to.go.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class AuthRequestModel(
    val role: Int,
    val email: String?,
    val password: String,
)

data class RestorePasswordModel(val email: String)
data class ConfirmCodeModel(val code: String, val email: String)
data class ResetPasswordModel(val email: String, val password: String)