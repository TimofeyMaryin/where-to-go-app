package com.where.to.go.internet.models

import java.time.LocalDateTime

data class Party(
    val id: Int,
    val ownerId: Int,
    val image : String,
    val about : String,
    val tg : String?,
    val price: Int,
    val theme: String?,
    val name: String,
    val date: LocalDateTime,
    val maxGuests: Int,
    val status : Int,
    val created: LocalDateTime
)