package com.where.to.go.core.cases.auth

import com.where.to.go.core.cases.user.UserUseCase
import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.ConfirmCodeDomain
import com.where.to.go.domain.ResetPasswordDomain
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.contract.AuthRepository
import com.where.to.go.domain.contract.UserRepository
import okhttp3.MultipartBody
import retrofit2.Response

class AuthUseCaseImpl(
    private val authRepository: AuthRepository
) : AuthUseCase {
    override suspend fun signup(user: AuthDomain): Response<ResponseDomain> {
        return authRepository.signup(user)
    }

    override suspend fun login(user: AuthDomain): Response<ResponseDomain> {
        return authRepository.login(user)
    }

    override suspend fun tokenUpdate(token: ResponseDomain, authToken: String): Response<ResponseDomain> {
        return authRepository.tokenUpdate(token, authToken)
    }

    override suspend fun restorePassword(data: ResponseDomain): Response<ResponseDomain> {
        return authRepository.restorePassword(data)
    }

    override suspend fun confirmCode(data: ConfirmCodeDomain): Response<ResponseDomain> {
        return authRepository.confirmCode(data)
    }

    override suspend fun resetPassword(
        data: ResetPasswordDomain,
        authToken: String
    ): Response<ResponseDomain> {
        return authRepository.resetPassword(data, authToken)
    }


}