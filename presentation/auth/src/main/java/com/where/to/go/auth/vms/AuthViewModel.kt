package com.where.to.go.auth.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {

    var userEmail by mutableStateOf("")
    var userPassword by mutableStateOf("")

    val enterUserName: (String) -> Unit = { email -> this.userEmail = email }
    val enterUserPassword: (String) -> Unit = { password -> this.userPassword = password }
    val clearUserData = { userEmail = ""; userPassword = ""; userAccType = -1 }

    fun isValidEmail(email: String = this.userEmail): Boolean {
        // Регулярное выражение для проверки формата электронной почты
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return !emailRegex.matches(email)
    }

    // auth
    var userAccType by mutableStateOf(-1)
    var enterUserAccType: (Int) -> Unit = { type -> userAccType = type }

    var userTelegram by mutableStateOf<String?>(null)
    var userVK by mutableStateOf<String?>(null)
    var userPhone by mutableStateOf<String?>(null)
}