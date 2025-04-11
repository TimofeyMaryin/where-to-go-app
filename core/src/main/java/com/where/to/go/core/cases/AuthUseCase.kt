package com.where.to.go.core.cases

import com.where.to.go.core.RetrofitClient
import com.where.to.go.core.data.dao.AuthService
import com.where.to.go.domain.model.AuthRequestModel
import com.where.to.go.domain.model.AuthResponseModel
import com.where.to.go.domain.model.ConfirmCodeModel
import com.where.to.go.domain.model.ResetPasswordModel
import com.where.to.go.domain.model.ResponseModel
import com.where.to.go.domain.model.RestorePasswordModel
import retrofit2.Response

class AuthUseCase: AuthService {

    override suspend fun signup(user: AuthRequestModel): Response<AuthResponseModel> {
        return RetrofitClient.authService.signup(user)
    }

    override suspend fun login(user: AuthRequestModel): Response<AuthResponseModel> {
        return RetrofitClient.authService.login(user)
    }

    override suspend fun tokenUpdate(
        token: AuthResponseModel,
        authToken: String
    ): Response<AuthResponseModel> {
        return RetrofitClient.authService.tokenUpdate(token, authToken)
    }

    override suspend fun restorePassword(data: RestorePasswordModel): Response<ResponseModel> {
        return RetrofitClient.authService.restorePassword(data)
    }

    override suspend fun confirmCode(data: ConfirmCodeModel): Response<AuthResponseModel> {
        return RetrofitClient.authService.confirmCode(data)
    }

    override suspend fun resetPassword(data: ResetPasswordModel, authToken: String): Response<ResponseModel> {
        return RetrofitClient.authService.resetPassword(data, authToken)
    }


}