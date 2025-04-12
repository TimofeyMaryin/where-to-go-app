package com.where.to.go.domain

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordDomain(val email: String, val password: String)