package com.where.to.go.core.data.dao

import com.where.to.go.core.data.dto.ResponseDto
import com.where.to.go.core.data.dto.UserDto
import com.where.to.go.domain.ResponseDomain
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UserService {
    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserDto>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserDto>

    @POST("users/find")
    suspend fun findUser(@Body email: ResponseDto): Response<UserDto?>

    @Multipart
    @POST("users/{id}/avatar")
    suspend fun uploadAvatar(
        @Path("id") userId: Int,
        @Part file: MultipartBody.Part,
        @Header("Authorization") authToken: String
    ): Response<ResponseDto>

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int,
        @Header("Authorization") authToken: String
    ): Response<String>

    @PUT("users/{id}/edit")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body userDto: UserDto,
        @Header("Authorization") authToken: String
    ): Response<UserDto>
}