package com.where.to.go.data.repository

import com.where.to.go.data.dao.UserService
import com.where.to.go.data.dto.ResponseDto
import com.where.to.go.data.mapper.ResponseMapper
import com.where.to.go.data.mapper.UserMapper
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

    override suspend fun updateUserProfile(userDomain: UserDomain, authToken: String): UserDomain {
        val response = userService.updateUser(
            userDomain.id,
            UserMapper.toDto(userDomain),
            authToken
        )
        if (response.isSuccessful) {
            val userDto = response.body() ?: throw Exception("Failed to update user")
            return UserMapper.toDomain(userDto)
        } else {
            throw Exception("Failed to update user: ${response.message()}")
        }
    }

    override suspend fun getAllUsers(): List<UserDomain> {
        val response = userService.getAllUsers()
        if (response.isSuccessful) {
            return response.body()?.map { UserMapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch users: ${response.message()}")
        }
    }

    override suspend fun getUser(id: Int): UserDomain {
        val response = userService.getUser(id)
        if (response.isSuccessful) {
            return response.body()?.let { UserMapper.toDomain(it) }
                ?: throw Exception("User not found")
        } else {
            throw Exception("Failed to fetch user: ${response.message()}")
        }
    }

    override suspend fun findUser(email: String): UserDomain? {
        val response = userService.findUser(email)
        if (response.isSuccessful) {
            return response.body()?.let { UserMapper.toDomain(it) }
        } else {
            throw Exception("Failed to find user: ${response.message()}")
        }
    }

    override suspend fun uploadAvatar(
        userId: Int,
        file: MultipartBody.Part,
        authToken: String
    ): ResponseDomain {
        val response = userService.uploadAvatar(userId, file, authToken)
        if (response.isSuccessful) {
            val responseDto = response.body() ?: throw Exception("Upload failed")
            return ResponseMapper.toDomain(responseDto)
        } else {
            throw Exception("Failed to upload avatar: ${response.message()}")
        }
    }

    override suspend fun deleteUser(id: Int, authToken: String): String {
        val response = userService.deleteUser(id, authToken)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Delete failed")
        } else {
            throw Exception("Failed to delete user: ${response.message()}")
        }
    }

    override suspend fun updateUser(id: Int, userDomain: UserDomain, authToken: String): UserDomain {
        val response = userService.updateUser(
            id,
            UserMapper.toDto(userDomain),
            authToken
        )
        if (response.isSuccessful) {
            val userDto = response.body() ?: throw Exception("Update failed")
            return UserMapper.toDomain(userDto)
        } else {
            throw Exception("Failed to update user: ${response.message()}")
        }
    }
}