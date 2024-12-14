package com.where.to.go.internet.dao

import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/signup") suspend fun signup(@Body user: AuthRequestModel): Response<AuthResponseModel>
    @POST("/auth/login") suspend fun login(@Body user: AuthRequestModel): Response<AuthResponseModel>
    @POST("/auth/token_login") suspend fun tokenUpdate(
        @Body token: AuthResponseModel,
        @Header("Authorization")authToken: String): Response<AuthResponseModel>
}