package com.where.to.go.internet.dao

import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface UserService {
    @GET("/test/users") suspend fun getAllUsers(): Response<List<User>>
    @POST("/test/users") suspend fun createUser(@Body user: AuthRequestModel): Response<AuthRequestModel>
    @GET suspend fun testCall(): Response<Any>
}