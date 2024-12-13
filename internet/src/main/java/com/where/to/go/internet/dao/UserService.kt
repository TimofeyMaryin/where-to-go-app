package com.where.to.go.internet.dao

import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface UserService {
    @GET("/users") suspend fun getAllUsers(): Response<List<User>>
    @GET suspend fun getUser(id: Int): Response<User>
    @GET suspend fun deleteUser(id: Int): Response<Any>
    @POST suspend fun editUser(id: Int, user: User): Response<User>
}