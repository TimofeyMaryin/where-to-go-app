package com.where.to.go.core.data.dao

import com.where.to.go.core.data.dto.AuthDto
import com.where.to.go.core.data.dto.ConfirmCodeDto
import com.where.to.go.core.data.dto.ResetPasswordDto
import com.where.to.go.core.data.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/signup")
    suspend fun signup(@Body user: AuthDto): Response<ResponseDto>

    @POST("/auth/login")
    suspend fun login(@Body user: AuthDto): Response<ResponseDto>

    @POST("/auth/token_login")
    suspend fun tokenUpdate(
        @Body token: ResponseDto,
        @Header("Authorization") authToken: String
    ): Response<ResponseDto>

    @POST("/auth/restore")
    suspend fun restorePassword(
        @Body data: ResponseDto
    ): Response<ResponseDto>

    @POST("/auth/confirm")
    suspend fun confirmCode(
        @Body data: ConfirmCodeDto
    ): Response<ResponseDto>

    @POST("/auth/reset_password")
    suspend fun resetPassword(
        @Body data: ResetPasswordDto,
        @Header("Authorization")authToken: String): Response<ResponseDto>
}