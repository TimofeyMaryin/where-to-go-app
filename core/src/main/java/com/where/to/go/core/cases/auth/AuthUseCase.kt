package com.where.to.go.core.cases.auth


import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.ConfirmCodeDomain
import com.where.to.go.domain.ResetPasswordDomain
import com.where.to.go.domain.ResponseDomain
import retrofit2.Response

interface AuthUseCase {

    suspend fun signup(user: AuthDomain): Response<ResponseDomain>

    suspend fun login(user: AuthDomain): Response<ResponseDomain>

    suspend fun tokenUpdate(
        token: ResponseDomain,
        authToken: String
    ): Response<ResponseDomain>

    suspend fun restorePassword(data: ResponseDomain): Response<ResponseDomain>

    suspend fun confirmCode(data: ConfirmCodeDomain): Response<ResponseDomain>

    suspend fun resetPassword(data: ResetPasswordDomain, authToken: String): Response<ResponseDomain>


}