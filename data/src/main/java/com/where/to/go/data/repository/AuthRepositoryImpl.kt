package com.where.to.go.data.repository

import com.where.to.go.data.dao.AuthService
import com.where.to.go.data.dao.UserService
import com.where.to.go.data.mapper.AuthMapper
import com.where.to.go.data.mapper.ConfirmCodeMapper
import com.where.to.go.data.mapper.ResetPasswordMapper
import com.where.to.go.data.mapper.ResponseMapper
import com.where.to.go.data.mapper.UserMapper
import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.ConfirmCodeDomain
import com.where.to.go.domain.ResetPasswordDomain
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.contract.AuthRepository
import com.where.to.go.domain.contract.UserRepository
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepositoryImpl(
    private val service: AuthService
) : AuthRepository {
    override suspend fun signup(domain: AuthDomain): Response<ResponseDomain> {
        val response = service.signup(AuthMapper.toDto(domain))
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { ResponseMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody() ?: ResponseBody.create(null, ""), response.raw())
        }
    }

    override suspend fun login(domain: AuthDomain): Response<ResponseDomain> {
        val response = service.login(AuthMapper.toDto(domain))
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { ResponseMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody() ?: ResponseBody.create(null, ""), response.raw())
        }
    }

    override suspend fun confirmCode(domain: ConfirmCodeDomain): Response<ResponseDomain> {
        val response = service.confirmCode(ConfirmCodeMapper.toDto(domain))
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { ResponseMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody() ?: ResponseBody.create(null, ""), response.raw())
        }
    }

    override suspend fun resetPassword(
        domain: ResetPasswordDomain,
        authToken: String
    ): Response<ResponseDomain> {
        val response = service.resetPassword(ResetPasswordMapper.toDto(domain), authToken)
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { ResponseMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody() ?: ResponseBody.create(null, ""), response.raw())
        }
    }

    override suspend fun restorePassword(domain: ResponseDomain): Response<ResponseDomain> {
        val response = service.restorePassword(ResponseMapper.toDto(domain))
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { ResponseMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody() ?: ResponseBody.create(null, ""), response.raw())
        }
    }

    override suspend fun tokenUpdate(token: ResponseDomain, authToken: String): Response<ResponseDomain> {
        val response = service.tokenUpdate(ResponseMapper.toDto(token), authToken)
        return if (response.isSuccessful) {
            Response.success(response.body()?.let { ResponseMapper.toDomain(it) })
        } else {
            Response.error(response.errorBody() ?: ResponseBody.create(null, ""), response.raw())
        }
    }
}