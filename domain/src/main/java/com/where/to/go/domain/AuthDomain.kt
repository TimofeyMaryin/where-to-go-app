package com.where.to.go.domain

import kotlinx.serialization.Serializable


@Serializable
data class AuthDomain(
    val role: Int,
    val email: String?,
    val password: String,
)