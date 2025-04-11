package com.where.to.go.core.cases

import com.where.to.go.core.RetrofitClient
import com.where.to.go.core.data.dao.PartyService
import com.where.to.go.domain.model.Party
import retrofit2.Response

class PartyUseCase: PartyService {

    override suspend fun createParty(event: Party, authToken: String): Response<String> {
        return RetrofitClient.partyService.createParty(event, authToken)
    }

    override suspend fun payParty(id: Int, authToken: String): Response<String> {
        return RetrofitClient.partyService.payParty(id, authToken)
    }

    override suspend fun deleteParty(id: Int, authToken: String): Response<String> {
        return RetrofitClient.partyService.payParty(id, authToken)
    }

    override suspend fun getAllParties(): Response<List<Party>> {
        return RetrofitClient.partyService.getAllParties()
    }

    override suspend fun getParty(id: Int): Response<Party> {
        return RetrofitClient.partyService.getParty(id)
    }

    override suspend fun getOwnerParties(id: Int): Response<List<Party>> {
        return RetrofitClient.partyService.getOwnerParties(id)
    }


}