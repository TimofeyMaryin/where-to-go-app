package com.where.to.go.data.mapper

import com.where.to.go.data.dto.ResponseDto
import com.where.to.go.data.dto.UserDto
import com.where.to.go.domain.ResponseDomain
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.UserRole
object ResponseMapper {
    fun toDomain(dto: ResponseDto): ResponseDomain {
        return ResponseDomain(response = dto.response)
    }

    fun toDto(domain: ResponseDomain): ResponseDto {
        return ResponseDto(response = domain.response)
    }
}