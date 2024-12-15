package com.where.to.go.internet.dao

import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.Party
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PartyService {
    @POST("/events/create") suspend fun createParty(
        @Body event: Party,
        @Header("Authorization") authToken: String): Response<String>

    @POST("/events/{id}/pay") suspend fun payParty(
        @Path("id") id: Int,
        @Header("Authorization") authToken: String): Response<String>

    @DELETE("/events/{id}/pay") suspend fun deleteParty(
        @Path("id") id: Int,
        @Header("Authorization") authToken: String): Response<String>

    @GET("/events") suspend fun getAllParties(): Response<List<Party>>
    @GET("/events/{id}") suspend fun getParty(@Path("id") id: Int): Response<Party>

    @GET("/events/owner/{id}") suspend fun getOwnerParties(@Path("id") id: Int): Response<List<Party>>
}