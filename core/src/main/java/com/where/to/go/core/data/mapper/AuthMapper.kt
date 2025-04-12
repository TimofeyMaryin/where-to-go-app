package com.where.to.go.core.data.mapper

import com.where.to.go.core.data.dto.AuthDto
import com.where.to.go.core.data.dto.UserDto
import com.where.to.go.domain.AuthDomain
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.UserRole

object AuthMapper {
    fun toDomain(dto: AuthDto): AuthDomain {
        return AuthDomain(
            role = dto.role,
            email = dto.email,
            password = dto.password
        )
    }

    fun toDto(domain: AuthDomain): AuthDto {
        return AuthDto(
            role = domain.role,
            email = domain.email?: "",
            password = domain.password
        )
    }
}