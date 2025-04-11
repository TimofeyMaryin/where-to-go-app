package com.where.to.go.domain.contract

import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.model.ResponseModel
import com.where.to.go.domain.model.RestorePasswordModel
import okhttp3.MultipartBody
import retrofit2.Response

interface UserRepository {
    suspend fun getUserProfile(userId: Int): UserDomain
    suspend fun updateUserProfile(userDomain: UserDomain): UserDomain
    suspend fun getAllUsers(): Response<List<UserDomain>>
    suspend fun getUser(id: Int): Response<UserDomain>
    suspend fun findUser(email: RestorePasswordModel): Response<UserDomain?>
    suspend fun uploadAvatar(userId: Int, file: MultipartBody.Part, authToken: String): Response<ResponseModel>
    suspend fun deleteUser(id: Int, authToken: String): Response<String>
    suspend fun updateUser(id: Int, userDomain: UserDomain, authToken: String): Response<UserDomain>
}