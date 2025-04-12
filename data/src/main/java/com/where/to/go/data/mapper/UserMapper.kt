package com.where.to.go.data.mapper

import com.where.to.go.data.dto.UserDto
import com.where.to.go.domain.UserDomain
import com.where.to.go.domain.UserRole

object UserMapper {
    fun toDomain(dto: UserDto): UserDomain {
        return UserDomain(
            id = dto.id,
            email = dto.email,
            name = dto.name,
            role = UserRole.fromId(dto.role).id,
            description = dto.description,
            status = dto.status,
            tg = dto.tg,
            vk = dto.vk,
            phone = dto.phone,
            region = dto.region,
            town = dto.town,
            created = dto.created,
            avatar = dto.avatar
        )
    }

    fun toDto(domain: UserDomain): UserDto {
        return UserDto(
            id = domain.id,
            email = domain.email,
            name = domain.name,
            role = domain.role,
            password = "",
            description = domain.description,
            status = domain.status,
            tg = domain.tg,
            vk = domain.vk,
            phone = domain.phone,
            region = domain.region,
            town = domain.town,
            created = domain.created,
            avatar = domain.avatar
        )
    }
}