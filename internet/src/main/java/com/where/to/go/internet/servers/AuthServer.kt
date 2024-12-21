package com.where.to.go.internet.servers

import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.PartyUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.ConfirmCodeModel
import com.where.to.go.internet.models.Party
import com.where.to.go.internet.models.ResetPasswordModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.plugins.TokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface AuthServerInterface{
    fun handleSignup(
        authUseCase: AuthUseCase,
        email: String,
        role: Int,
        password: String,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun handleLogin(
        authUseCase: AuthUseCase,
        email: String,
        role: Int,
        password: String,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun restorePassword(
        authUseCase: AuthUseCase,
        model: RestorePasswordModel,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun confirmCode(
        authUseCase: AuthUseCase,
        model: ConfirmCodeModel,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (AuthResponseModel?) -> Unit,
        onError: (String) -> Unit
    )

    fun resetPassword(
        authUseCase: AuthUseCase,
        tokenManager: TokenManager,
        model: ResetPasswordModel,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun updateToken(
        authUseCase: AuthUseCase,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )
}

class AuthServer {
    companion object : AuthServerInterface{
        override fun handleSignup(
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

        override fun handleLogin(
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

        override fun updateToken(
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
                    val token = tokenManager.getToken()
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

        override fun restorePassword(
            authUseCase: AuthUseCase,
            model: RestorePasswordModel,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = authUseCase.restorePassword(model)
                    if (response.isSuccessful) {
                        onResult(response.message())
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

        override fun confirmCode(
            authUseCase: AuthUseCase,
            model: ConfirmCodeModel,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (AuthResponseModel?) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = authUseCase.confirmCode(model)
                    if (response.isSuccessful) {
                        onResult(response.body())
                    } else {
                        onError(response.raw().toString() + " " + response.message())
                    }
                } catch (e: Exception) {
                    onError("${e.message}")
                } finally {
                    onLoading(false)
                }
            }
        }

        override fun resetPassword(
            authUseCase: AuthUseCase,
            tokenManager: TokenManager,
            model: ResetPasswordModel,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = authUseCase.resetPassword(model, getAuthHeader(tokenManager.getToken()))
                    if (response.isSuccessful) {
                        onResult(response.message())
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