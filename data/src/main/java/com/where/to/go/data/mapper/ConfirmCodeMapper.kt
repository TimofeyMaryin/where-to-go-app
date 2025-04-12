package com.where.to.go.data.mapper

import com.where.to.go.data.dto.ConfirmCodeDto
import com.where.to.go.domain.ConfirmCodeDomain

object ConfirmCodeMapper {
    fun toDomain(dto: ConfirmCodeDto): ConfirmCodeDomain {
        return ConfirmCodeDomain(code = dto.code, email = dto.email)
    }

    fun toDto(domain: ConfirmCodeDomain): ConfirmCodeDto {
        return ConfirmCodeDto(code = domain.code, email = domain.email)
    }
}