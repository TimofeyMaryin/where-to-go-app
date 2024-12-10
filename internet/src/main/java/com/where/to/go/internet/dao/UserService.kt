package com.where.to.go.internet.dao

import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface UserService {
    @GET("/users") suspend fun getAllUsers(): Response<List<User>>
    @GET("/users/{id}") suspend fun getUser(@Path("id") id: Int): Response<User>
    @GET suspend fun testCall(): Response<Any>
    @DELETE("/user/{id}") suspend fun deleteUser(@Path("id") id: Int): Response<Any>

    @PUT("/users/{id}") suspend fun editUser(@Path("id") id: Int, @Body user: User): Response<User>
}