package com.where.to.go.domain.contract

import com.where.to.go.domain.PartyDomain

interface PartyRepository {
    suspend fun getParties(): List<PartyDomain>
    suspend fun scheduleParty(party: PartyDomain): PartyDomain
}