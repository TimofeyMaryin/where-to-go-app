package com.where.to.go.core.repository

import com.where.to.go.core.data.dao.UserService
import com.where.to.go.core.data.dto.ResponseDto
import com.where.to.go.core.data.mapper.ResponseMapper
import com.where.to.go.core.data.mapper.UserMapper
import com.where.to.go.domain.contract.UserRepository
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.ResponseDomain
import okhttp3.MultipartBody
import retrofit2.Response

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun getUserProfile(userId: Int): UserDomain {
        val response = userService.getUser(userId)
        if (response.isSuccessful) {
            val userDto = response.body() ?: throw Exception("User not found")
            return UserMapper.toDomain(userDto)
        } else {
            throw Exception("Failed to fetch user: ${response.message()}")
        }
    }

    override suspend fun updateUserProfile(userDomain: UserDomain): UserDomain {
        val response = userService.updateUser(userDomain.id, UserMapper.toDto(userDomain), "")
        if (response.isSuccessful) {
            val userDto = response.body() ?: throw Exception("Failed to update user")
            return UserMapper.toDomain(userDto)
        } else {
            throw Exception("Failed to update user: ${response.message()}")
        }
    }

    override suspend fun getAllUsers(): Response<List<UserDomain>> {
        val response = userService.getAllUsers()
        return if (response.isSuccessful) {
            Response.success(response.body()?.map { UserMapper.toDomain(it) } ?: emptyList())
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }

    override suspend fun getUser(id: Int): Response<UserDomain> {
        val response = userService.getUser(id)
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { UserMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }

    override suspend fun findUser(email: ResponseDomain): Response<UserDomain?> {
        val response = userService.findUser(ResponseMapper.toDto(email))
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { UserMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }

    override suspend fun uploadAvatar(
        userId: Int,
        file: MultipartBody.Part,
        authToken: String
    ): Response<ResponseDomain> {
        return userService.uploadAvatar(userId, file,  authToken)
    }

    override suspend fun deleteUser(id: Int, authToken: String): Response<String> {
        return userService.deleteUser(id, authToken)
    }

    override suspend fun updateUser(id: Int, userDomain: UserDomain, authToken: String): Response<UserDomain> {
        val response = userService.updateUser(id, UserMapper.toDto(userDomain), authToken)
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { UserMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }
}