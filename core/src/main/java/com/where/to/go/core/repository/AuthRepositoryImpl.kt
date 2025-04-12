package com.where.to.go.core.repository

import com.where.to.go.core.data.dao.AuthService
import com.where.to.go.core.data.dao.UserService
import com.where.to.go.core.data.mapper.AuthMapper
import com.where.to.go.core.data.mapper.ResponseMapper
import com.where.to.go.core.data.mapper.UserMapper
import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.ConfirmCodeDomain
import com.where.to.go.domain.ResetPasswordDomain
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.contract.AuthRepository
import com.where.to.go.domain.contract.UserRepository
import okhttp3.MultipartBody
import retrofit2.Response

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {
    override suspend fun signup(domain: AuthDomain): ResponseDomain{
        val response = service.signup(AuthMapper.toDto(domain))
        if (response.isSuccessful) {
            val dto = response.body() ?: throw Exception("User not found")
            return ResponseMapper.toDomain(dto)
        } else {
            throw Exception("Failed to signup: ${response.message()}")
        }
    }

    override suspend fun login(domain: AuthDomain): ResponseDomain {
        val response = service.login(AuthMapper.toDto(domain))
        if (response.isSuccessful) {
            val dto = response.body() ?: throw Exception("User not found")
            return ResponseMapper.toDomain(dto)
        } else {
            throw Exception("Failed to login: ${response.message()}")
        }
    }

    override suspend fun confirmCode(domain: ConfirmCodeDomain): Response<ResponseDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(domain: ResetPasswordDomain): Response<ResponseDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun restorePassword(domain: ResponseDomain): Response<ResponseDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun tokenUpdate(token: ResponseDomain): Response<ResponseDomain> {
        TODO("Not yet implemented")
    }



}