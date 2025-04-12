package com.where.to.go.core.data.dto

import com.google.gson.annotations.SerializedName

data class ConfirmCodeDto(
    @SerializedName("code") val id: String,
    @SerializedName("email") val email: String
)