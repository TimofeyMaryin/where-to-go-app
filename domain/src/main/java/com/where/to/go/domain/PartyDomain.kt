package com.where.to.go.domain

import kotlinx.serialization.Serializable

//TODO add category&&location fields
@Serializable
data class PartyDomain(
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

enum class PartyCategory{
    Xmas,
    Birthday
}