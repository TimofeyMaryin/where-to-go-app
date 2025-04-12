package com.where.to.go.domain.contract

import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.ConfirmCodeDomain
import com.where.to.go.domain.ResetPasswordDomain
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import okhttp3.MultipartBody
import retrofit2.Response


interface AuthRepository {
    suspend fun signup(domain: AuthDomain): Response<ResponseDomain>
    suspend fun login(domain: AuthDomain): Response<ResponseDomain>
    suspend fun confirmCode(domain: ConfirmCodeDomain): Response<ResponseDomain>
    suspend fun resetPassword(domain: ResetPasswordDomain, authToken: String): Response<ResponseDomain>
    suspend fun restorePassword(domain: ResponseDomain): Response<ResponseDomain>
    suspend fun tokenUpdate(token: ResponseDomain, authToken: String): Response<ResponseDomain>

}