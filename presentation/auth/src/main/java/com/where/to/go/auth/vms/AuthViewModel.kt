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


    fun isValidEmail(email: String = this.userEmail): Boolean {
        // Регулярное выражение для проверки формата электронной почты
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return !emailRegex.matches(email)
    }

    var enterUserAccType: (Int) -> Unit = { type -> userRole = type }

}