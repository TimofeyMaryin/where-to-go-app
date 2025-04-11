package com.where.to.go.domain

import kotlinx.serialization.Serializable


@Serializable
data class UserDomain(
    val id: Int,
    val email: String,
    val name: String?,
    val role: Int,
    val description: String?,
    val status: String?,
    val avatar: String?,
    val tg: String?,
    val vk: String?,
    val phone: String?,
    val region: String?,
    val town: String?,
    val created: String
)

enum class UserRole(val id: Int) {
    GUEST(0),
    ORGANIZER(1);
    companion object {
        fun fromId(id: Int): UserRole {
            return entries.find { it.id == id } ?: ORGANIZER
        }
    }
}
