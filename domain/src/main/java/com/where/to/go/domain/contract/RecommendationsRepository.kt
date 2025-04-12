package com.where.to.go.domain.contract

import com.where.to.go.domain.PartyDomain

interface RecommendationsRepository {
    suspend fun getRecommendations(userId: String): List<PartyDomain>
}