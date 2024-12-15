package com.where.to.go.internet.models

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class User(
    val id: Int,
    val email: String,
    val name: String?,
    val role: Int,
    val password: String,
    val description: String?,
    val status: String?,
    val avatar: String?,
    val tg: String?,
    val vk: String?,
    val phone: String?,
    val region: String?,
    val town: String?,
    val created: LocalDateTime
)

