package com.where.to.go.core.cases

import com.where.to.go.data.RetrofitClient
import com.where.to.go.domain.PartyDomain
import retrofit2.Response

class PartyUseCase: com.where.to.go.data.dao.PartyService {

    override suspend fun createParty(event: PartyDomain, authToken: String): Response<String> {
        return RetrofitClient.partyService.createParty(event, authToken)
    }

    override suspend fun payParty(id: Int, authToken: String): Response<String> {
        return RetrofitClient.partyService.payParty(id, authToken)
    }

    override suspend fun deleteParty(id: Int, authToken: String): Response<String> {
        return RetrofitClient.partyService.payParty(id, authToken)
    }

    override suspend fun getAllParties(): Response<List<PartyDomain>> {
        return RetrofitClient.partyService.getAllParties()
    }

    override suspend fun getParty(id: Int): Response<PartyDomain> {
        return RetrofitClient.partyService.getParty(id)
    }

    override suspend fun getOwnerParties(id: Int): Response<List<PartyDomain>> {
        return RetrofitClient.partyService.getOwnerParties(id)
    }


}