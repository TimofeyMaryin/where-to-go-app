package com.where.to.go.main.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.User
import com.where.to.go.internet.plugins.TokenManager
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val userRetrofit by mutableStateOf(RetrofitClient.userService)

    var loginUser by mutableStateOf<User?>(null)

    fun updateUserData(
        newUser: User,
        callback: UserDataChangedCallback
    ) {
        viewModelScope.launch {
            val responseLogin = AuthUseCase()
                .login(
                    user = AuthRequestModel(
                        role = loginUser!!.role,
                        email = loginUser!!.email,
                        password = loginUser!!.password
                    )
                )

            if (!responseLogin.isSuccessful) {
                callback.onError(
                    """
                        responseLogin is not successful!
                        Errorbody: ${responseLogin.errorBody()}
                        Message: ${responseLogin.message()}
                    """.trimIndent()
                )
                return@launch
            }
            val token: String = responseLogin.body()?.token ?: throw IllegalArgumentException("responseLogin.body() is null")
            TokenManager.saveToken(token)

            val response = userRetrofit.editUser(
                id = loginUser?.id ?: throw IllegalArgumentException("Cannot get user ID."),
                user = newUser,
                authToken = token
            )

            if (response.isSuccessful) {
                loginUser = response.body() ?: throw IllegalArgumentException("Cannot get User from response.body()")
                callback.onSuccess("Kaif")
            } else {
                callback.onError(
                    """
                        Response is not success message: ${response.message()}
                        Response errorBody: ${response.errorBody()}
                        TokenManager.getToke() = ${TokenManager.getToken()}
                    """.trimIndent()
                )
            }

        }

    }

}

interface UserDataChangedCallback {
    fun onError(msg: String)
    fun onSuccess(msg: String)
}