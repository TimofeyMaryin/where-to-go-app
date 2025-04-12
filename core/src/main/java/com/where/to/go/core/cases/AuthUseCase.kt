package com.where.to.go.core.cases

import com.where.to.go.core.RetrofitClient
import com.where.to.go.core.data.dao.AuthService
import com.where.to.go.core.data.dto.ResponseDto
import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.ConfirmCodeModel
import com.where.to.go.domain.ResetPasswordModel
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.RestorePasswordModel
import retrofit2.Response

class AuthUseCase: AuthService {

    override suspend fun signup(user: AuthDomain): Response<ResponseDto> {
        return RetrofitClient.authService.signup(user)
    }

    override suspend fun login(user: AuthDomain): Response<ResponseDto> {
        return RetrofitClient.authService.login(user)
    }

    override suspend fun tokenUpdate(
        token: ResponseDomain,
        authToken: String
    ): Response<ResponseDto> {
        return RetrofitClient.authService.tokenUpdate(token, authToken)
    }

    override suspend fun restorePassword(data: RestorePasswordModel): Response<ResponseDomain> {
        return RetrofitClient.authService.restorePassword(data)
    }

    override suspend fun confirmCode(data: ConfirmCodeModel): Response<ResponseDto> {
        return RetrofitClient.authService.confirmCode(data)
    }

    override suspend fun resetPassword(data: ResetPasswordModel, authToken: String): Response<ResponseDto> {
        return RetrofitClient.authService.resetPassword(data, authToken)
    }


}