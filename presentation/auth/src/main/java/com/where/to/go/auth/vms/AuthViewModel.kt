package com.where.to.go.auth.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.ConfirmCodeModel
import com.where.to.go.internet.models.RequestState
import com.where.to.go.internet.models.ResetPasswordModel
import com.where.to.go.internet.models.ResponseModel
import com.where.to.go.internet.models.RestorePasswordModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    var sendable by mutableStateOf(false)

    fun checkSendable(){
        sendable = !isValidEmail() && userPassword.length > 4 && userRole > -1
    }

    fun isValidEmail(email: String = this.userEmail): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return !emailRegex.matches(email)
    }

    var enterUserAccType: (Int) -> Unit = { type -> userRole = type }

    /*** Server ***/
    private val authUseCase = AuthUseCase()
    private val tokenManager = TokenManager

    val resetPasswordState = MutableLiveData<RequestState<ResponseModel>>()
    fun resetPassword(model: ResetPasswordModel) {
        resetPasswordState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = authUseCase.resetPassword(model, tokenManager.getToken())
                if (response.isSuccessful) {
                    resetPasswordState.value = RequestState(data = response.body())
                } else {
                    resetPasswordState.value = RequestState(error = "Ошибка: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                resetPasswordState.value = RequestState(error = "Ошибка: ${e.message}")
            }
        }
    }

    val loginState = MutableLiveData<RequestState<AuthResponseModel>>()
    fun login(model: AuthRequestModel) {
        loginState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = authUseCase.login(model)
                if (response.isSuccessful) {
                    loginState.value = RequestState(data = response.body())
                } else {
                    loginState.value = RequestState(error = "Ошибка: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                loginState.value = RequestState(error = "Ошибка: ${e.message}")
            }
        }
        loginState.value = RequestState(isLoading = false)
    }

    val signupState = MutableLiveData<RequestState<AuthResponseModel>>()
    fun signup(model: AuthRequestModel) {
        signupState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = authUseCase.signup(model)
                if (response.isSuccessful) {
                    signupState.value = RequestState(data = response.body())
                } else {
                    signupState.value = RequestState(error = "Ошибка: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                signupState.value = RequestState(error = "Ошибка: ${e.message}")
            }
        }
        signupState.value = RequestState(isLoading = false)
    }

    val autoLoginState = MutableLiveData<RequestState<AuthResponseModel>>()
    fun autoLogin() {
        autoLoginState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val model = AuthResponseModel(tokenManager.getToken())
                val header = tokenManager.getHeaderToken()
                val response = authUseCase.tokenUpdate(model, header)
                if (response.isSuccessful) {
                    autoLoginState.value = RequestState(data = response.body())
                } else {
                    autoLoginState.value = RequestState(error = "Ошибка: ${response.raw()}")
                }
            } catch (e: Exception) {
                autoLoginState.value = RequestState(error = "Ошибка: ${e.message}")
            }
        }
    }

    val confirmCodeState = MutableLiveData<RequestState<AuthResponseModel>>()
    fun confirmCode(model: ConfirmCodeModel) {
        confirmCodeState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = authUseCase.confirmCode(model)
                if (response.isSuccessful) {
                    confirmCodeState.value = RequestState(data = response.body())
                } else {
                    confirmCodeState.value = RequestState(error = "Ошибка: ${response.raw()}")
                }
            } catch (e: Exception) {
                confirmCodeState.value = RequestState(error = "Ошибка: ${e.message}")
            }
        }
    }

    val restorePasswordState = MutableLiveData<RequestState<ResponseModel>>()
    fun restorePassword(model: RestorePasswordModel) {
        restorePasswordState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = authUseCase.restorePassword(model)
                if (response.isSuccessful) {
                    restorePasswordState.value = RequestState(data = response.body())
                } else {
                    restorePasswordState.value = RequestState(error = "Ошибка: ${response.raw()}")
                }
            } catch (e: Exception) {
                restorePasswordState.value = RequestState(error = "Ошибка: ${e.message}")
            }
        }
    }


}