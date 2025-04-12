package com.where.to.go.data.mapper

import com.where.to.go.data.dto.ResetPasswordDto
import com.where.to.go.data.dto.ResponseDto
import com.where.to.go.domain.ResetPasswordDomain
import com.where.to.go.domain.ResponseDomain

object ResetPasswordMapper {
    fun toDomain(dto: ResetPasswordDto): ResetPasswordDomain {
        return ResetPasswordDomain(email = dto.email, password = dto.password)
    }

    fun toDto(domain: ResetPasswordDomain): ResetPasswordDto {
        return ResetPasswordDto(email = domain.email, password = domain.password)
    }
}