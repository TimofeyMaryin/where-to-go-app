package com.where.to.go.data.dto

import com.google.gson.annotations.SerializedName

data class ConfirmCodeDto(
    @SerializedName("code") val code: String,
    @SerializedName("email") val email: String
)