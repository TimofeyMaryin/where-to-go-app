package com.where.to.go.domain.contract

import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.ResponseDomain
import okhttp3.MultipartBody
import retrofit2.Response

interface UserRepository {
    suspend fun getUserProfile(userId: Int): UserDomain
    suspend fun updateUserProfile(userDomain: UserDomain, authToken: String): UserDomain
    suspend fun getAllUsers(): List<UserDomain>
    suspend fun getUser(id: Int): UserDomain
    suspend fun findUser(email: String): UserDomain?
    suspend fun uploadAvatar(userId: Int, file: MultipartBody.Part, authToken: String): ResponseDomain
    suspend fun deleteUser(id: Int, authToken: String): String
    suspend fun updateUser(id: Int, userDomain: UserDomain, authToken: String): UserDomain
}