package com.where.to.go.internet.models

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Party(
    val id: Int,
    val ownerId: Int,
    val image : String,
    val about : String,
    val tg : String?,
    val price: Int,
    val theme: String?,
    val name: String,
    val date: String,
    val maxGuests: Int,
    val status : Int,
    val created: String
)