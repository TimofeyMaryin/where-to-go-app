package com.where.to.go.core.cases.user

import com.where.to.go.data.RetrofitClient
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import okhttp3.MultipartBody
import retrofit2.Response

interface UserUseCase {
    suspend fun getAllUsers(): List<UserDomain>
    suspend fun getUser(id: Int): UserDomain
    suspend fun findUser(email: String): UserDomain?
    suspend fun uploadAvatar(userId: Int, file: MultipartBody.Part, authToken: String): ResponseDomain
    suspend fun deleteUser(id: Int, authToken: String): String
    suspend fun updateUser(id: Int, user: UserDomain, authToken: String): UserDomain
}
