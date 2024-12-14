package com.where.to.go.internet.plugins

import android.util.Log
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ServerHelper{
    companion object{
        fun handleSignup(
            authUseCase: AuthUseCase,
            email: String,
            //phone: String,
            role: Int,
            password: String,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = authUseCase.signup(AuthRequestModel(email = email, password = password, role = role))
                    if (response.isSuccessful) {
                        onResult("Reponse: " + response.message())
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

        fun handleLogin(
            authUseCase: AuthUseCase,
            email: String,
            role: Int,
            password: String,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = authUseCase.login(AuthRequestModel(email = email, password = password, role = role))
                    if (response.isSuccessful) {
                        val token = response.body()?.token ?: "Токен отсутствует"
                        onResult(token)
                    } else {
                        onError(response.raw().toString())
                    }
                } catch (e: Exception) {
                    onError("${e.message}")
                } finally {
                    onLoading(false)
                }
            }
        }

        fun updateToken(
            authUseCase: AuthUseCase,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val token = TokenManager.getToken()
                    val model = AuthResponseModel(token=token)
                    val response = authUseCase.tokenUpdate(model, getAuthHeader(token))
                    if (response.isSuccessful) {
                        val newToken = response.body()?.token ?: "Токен отсутствует"
                        onResult(newToken)
                    } else {
                        onError(response.raw().toString())
                    }
                } catch (e: Exception) {
                    onError("${e.message}")
                } finally {
                    onLoading(false)
                }
            }
        }

        fun getAuthHeader(token: String): String{
            return "Bearer  $token"
        }
    }




}