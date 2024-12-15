package com.where.to.go.internet.cases

import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.dao.UserService
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.models.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body

class UserUseCase: UserService {
    override suspend fun getAllUsers(): Response<List<User>> {
        return RetrofitClient.userService.getAllUsers()
    }

    override suspend fun getUser(id: Int): Response<User> {
        return RetrofitClient.userService.getUser(id)
    }

    override suspend fun findUser(email: RestorePasswordModel): Response<User> {
        return RetrofitClient.userService.findUser(email)
    }

    override suspend fun uploadAvatar(userId: Int, file: MultipartBody.Part, authToken: String): Response<String> {
        return RetrofitClient.userService.uploadAvatar(userId, file, authToken)
    }

    override suspend fun deleteUser(id: Int, authToken: String): Response<String> {
        return RetrofitClient.userService.deleteUser(id, authToken)
    }

    override suspend fun editUser(id: Int, user: User, authToken: String): Response<User> {
        return RetrofitClient.userService.editUser(id, user, authToken)
    }
}