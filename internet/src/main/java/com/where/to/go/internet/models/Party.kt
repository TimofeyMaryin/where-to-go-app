package com.where.to.go.internet.models

data class Party(
    val ownerId: Int,
    val image : String?,
    val about : String,
    val tg : String?,
    val price: Int,
    val theme: String?,
    val name: String,
    val data: String,
    val maxGuests: Int,
    val status : Int,
)