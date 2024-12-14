package com.where.to.go.auth.vms

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.where.to.go.internet.plugins.TokenManager
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
    var restoreCode by mutableStateOf("")
    var userPassword by mutableStateOf("")
    var userRole by mutableStateOf(-1)

    var userPhone by mutableStateOf("")
    var userTelegram by mutableStateOf<String>("")
    var userVK by mutableStateOf<String>("")

    val enterUserName: (String) -> Unit = { email -> this.userEmail = email }
    val enterRestoreCode: (String) -> Unit = { restoreCode -> this.restoreCode = restoreCode }
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



}