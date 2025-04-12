package com.where.to.go.core.cases.user

import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.contract.UserRepository
import okhttp3.MultipartBody

class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {
    override suspend fun getAllUsers(): List<UserDomain> {
        return userRepository.getAllUsers()
    }

    override suspend fun getUser(id: Int): UserDomain {
        return userRepository.getUser(id)
    }

    override suspend fun findUser(email: String): UserDomain? {
        return userRepository.findUser(email)
    }

    override suspend fun uploadAvatar(userId: Int, file: MultipartBody.Part, authToken: String): ResponseDomain {
        return userRepository.uploadAvatar(userId, file, authToken)
    }

    override suspend fun deleteUser(id: Int, authToken: String): String {
        return userRepository.deleteUser(id, authToken)
    }

    override suspend fun updateUser(id: Int, user: UserDomain, authToken: String): UserDomain {
        return userRepository.updateUser(id, user, authToken)
    }
}