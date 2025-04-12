package com.where.to.go.core.cases

import com.where.to.go.core.RetrofitClient
import com.where.to.go.core.data.dao.UserService
import com.where.to.go.core.data.dto.ResponseDto
import com.where.to.go.core.data.dto.UserDto
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.RestorePasswordModel
import com.where.to.go.domain.UserDomain
import okhttp3.MultipartBody
import retrofit2.Response

class UserUseCase: UserService {
    override suspend fun getAllUsers(): Response<List<UserDomain>> {
        return RetrofitClient.userService.getAllUsers()
    }

    override suspend fun getUser(id: Int): Response<UserDomain> {
        return RetrofitClient.userService.getUser(id)
    }

    override suspend fun findUser(email: ResponseDto): Response<UserDto?> {
        return RetrofitClient.userService.findUser(email)
    }

    override suspend fun uploadAvatar(userId: Int, file: MultipartBody.Part, authToken: String): Response<ResponseDto> {
        return RetrofitClient.userService.uploadAvatar(userId, file, authToken)
    }

    override suspend fun deleteUser(id: Int, authToken: String): Response<String> {
        return RetrofitClient.userService.deleteUser(id, authToken)
    }

    override suspend fun updateUser(id: Int, dto: UserDto, authToken: String): Response<UserDto> {
        return RetrofitClient.userService.updateUser(id, dto, authToken)
    }
}