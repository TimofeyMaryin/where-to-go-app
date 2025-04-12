package com.where.to.go.domain

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmCodeDomain(val code: String, val email: String)