package com.where.to.go.internet.servers

import com.where.to.go.internet.cases.PartyUseCase
import com.where.to.go.internet.models.Party
import com.where.to.go.internet.plugins.TokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface PartyServerInterface{
    fun getParties(
        partyUseCase: PartyUseCase,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (List<Party>) -> Unit,
        onError: (String) -> Unit
    )

    fun getParty(
        partyUseCase: PartyUseCase,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (List<Party>) -> Unit,
        onError: (String) -> Unit
    )

    fun getUserParty(
        partyUseCase: PartyUseCase,
        userId: Int,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (List<Party>) -> Unit,
        onError: (String) -> Unit
    )

    fun createParty(
        partyUseCase: PartyUseCase,
        party: Party,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun deleteParty(
        partyUseCase: PartyUseCase,
        id: Int,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )
}

class PartyServer {
    companion object : PartyServerInterface{
        override fun getParties(
            partyUseCase: PartyUseCase,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (List<Party>) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = partyUseCase.getAllParties()
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("Ошибка: ${response.raw()}")
                    }
                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }
        }

        override fun getParty(
            partyUseCase: PartyUseCase,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (List<Party>) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = partyUseCase.getAllParties()
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("Ошибка: ${response.raw()}")
                    }
                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }
        }

        override fun getUserParty(
            partyUseCase: PartyUseCase,
            userId: Int,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (List<Party>) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = partyUseCase.getOwnerParties(userId)
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("Ошибка: ${response.raw()}")
                    }
                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }
        }

        override fun createParty(
            partyUseCase: PartyUseCase,
            party: Party,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = partyUseCase.createParty(party, tokenManager.getToken())
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("Ошибка: ${response.raw()}")
                    }
                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }        
        }

        override fun deleteParty(
            partyUseCase: PartyUseCase,
            id: Int,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = partyUseCase.deleteParty(id, tokenManager.getToken())
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("Ошибка: ${response.raw()}")
                    }
                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }               }
    }
}