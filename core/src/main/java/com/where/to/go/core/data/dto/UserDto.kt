package com.where.to.go.core.data.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String?,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("role") val role: Int,
    @SerializedName("password") val password: String,
    @SerializedName("description") val description: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("tg") val tg: String?,
    @SerializedName("vk") val vk: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("region") val region: String?,
    @SerializedName("town") val town: String?,
    @SerializedName("created") val created: String
)