package com.where.to.go.domain.contract

import com.where.to.go.domain.model.Party

interface RecommendationsRepository {
    suspend fun getRecommendations(userId: String): List<Party>
}