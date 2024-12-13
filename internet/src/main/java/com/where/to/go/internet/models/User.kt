package com.where.to.go.internet.models

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val name: String?,
    val role: Int,
    val password: String,
    val description: String?,
    val status: String?,
    val avatar: String?,
    val tg: String?,
    val vk: String?,
    val email: String?,
    val phone: String?,
    val created: String,
    val location: String?
)

