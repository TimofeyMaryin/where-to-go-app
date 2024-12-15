package com.where.to.go.internet.dao

import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.models.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UserService {
    @GET("/users") suspend fun getAllUsers(): Response<List<User>>
    @GET("users/{id}") suspend fun getUser(@Path("id") id: Int): Response<User>
    @POST("users/find") suspend fun findUser(@Body email: RestorePasswordModel): Response<User>
    @POST("users/{id}/avatar") suspend fun uploadAvatar(@Path("id") userId: Int,
                                                        @Part file: MultipartBody.Part,
                                                        @Header("Authorization") authToken: String): Response<String>

    @DELETE("users/{id}") suspend fun deleteUser(
        @Path("id") id: Int,
        @Header("Authorization") authToken: String): Response<String>

    @PUT("users/{id}/edit")suspend fun editUser(
        @Path("id") id: Int, @Body user: User,
        @Header("Authorization") authToken: String): Response<User>
}