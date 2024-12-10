package com.where.to.go.internet.cases

import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.dao.AuthService
import com.where.to.go.internet.dao.UserService
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.User
import retrofit2.Response

class AuthUseCase: AuthService {

    override suspend fun signup(user: AuthRequestModel): Response<AuthResponseModel> {
        return RetrofitClient.authService.signup(user)
    }

    override suspend fun login(user: AuthRequestModel): Response<AuthResponseModel> {
        return RetrofitClient.authService.login(user)
    }


}