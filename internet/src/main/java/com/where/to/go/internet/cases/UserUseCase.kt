package com.where.to.go.internet.cases

import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.dao.UserService
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.User
import retrofit2.Response

class UserUseCase: UserService {
    override suspend fun getAllUsers(): Response<List<User>> {
        return RetrofitClient.instance.getAllUsers()
    }

    override suspend fun createUser(user: AuthRequestModel): Response<AuthRequestModel> {
        return RetrofitClient.instance.createUser(user)
    }

    override suspend fun testCall(): Response<Any> {
        return RetrofitClient.instance.testCall()
    }

    override suspend fun signup(user: AuthRequestModel): Response<AuthRequestModel> {
        return RetrofitClient.instance.signup(user)
    }

    override suspend fun login(user: AuthRequestModel): Response<AuthRequestModel> {
        return RetrofitClient.instance.login(user)
    }

}