package com.where.to.go.auth.vms

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.where.to.go.auth.plugins.TokenManager
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {

    var userEmail by mutableStateOf("")
    var userPassword by mutableStateOf("")
    var userRole by mutableStateOf(-1)

    var userPhone by mutableStateOf("")
    var userTelegram by mutableStateOf<String>("")
    var userVK by mutableStateOf<String>("")

    val enterUserName: (String) -> Unit = { email -> this.userEmail = email }
    val enterUserPassword: (String) -> Unit = { password -> this.userPassword = password }
    val clearUserData = {
        userEmail = "";
        userPassword = "";
        userRole = -1
        userPhone = ""
        userTelegram = ""
        userVK = ""
    }

    var sendable by mutableStateOf<Boolean>(false)

    fun checkSendable(){
        sendable = !isValidEmail() && userPassword.length > 4 && userRole > -1
    }

    fun isValidEmail(email: String = this.userEmail): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return !emailRegex.matches(email)
    }

    var enterUserAccType: (Int) -> Unit = { type -> userRole = type }

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
                    onResult("Успешный вход: $token")
                    TokenManager.saveToken(token)
                } else {
                    onError(response.message())
                }
            } catch (e: Exception) {
                onError("${e.message}")
            } finally {
                onLoading(false)
            }
        }
    }

}