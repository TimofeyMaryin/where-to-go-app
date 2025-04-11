package com.where.to.go.domain.contract

import com.where.to.go.domain.model.Party

interface PartyRepository {
    suspend fun getParties(): List<Party>
    suspend fun scheduleParty(party: Party): Party
}