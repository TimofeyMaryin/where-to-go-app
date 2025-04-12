package com.where.to.go.data.dto

import com.google.gson.annotations.SerializedName


data class ResetPasswordDto(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
    )