package com.where.to.go.internet.cases

import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.dao.AuthService
import com.where.to.go.internet.dao.UserService
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.ConfirmCodeModel
import com.where.to.go.internet.models.ResetPasswordModel
import com.where.to.go.internet.models.ResponseModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.models.User
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