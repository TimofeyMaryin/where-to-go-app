package com.where.to.go.internet.cases

import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.dao.UserService
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.User
import retrofit2.Response

class UserUseCase: UserService {
    override suspend fun getAllUsers(): Response<List<User>> {
        return RetrofitClient.userService.getAllUsers()
    }

    override suspend fun getUser(id: Int): Response<User> {
        return RetrofitClient.userService.getUser(id)
    }

    override suspend fun testCall(): Response<Any> {
        return RetrofitClient.userService.testCall()
    }

    override suspend fun deleteUser(id: Int): Response<Any> {
        return RetrofitClient.userService.deleteUser(id)
    }

    override suspend fun editUser(id: Int, user: User): Response<User> {
        return RetrofitClient.userService.editUser(id, user)
    }

    override suspend fun signup(user: AuthRequestModel): Response<AuthRequestModel> {
        return RetrofitClient.instance.signup(user)
    }

    override suspend fun login(user: AuthRequestModel): Response<AuthRequestModel> {
        return RetrofitClient.instance.login(user)
    }

}