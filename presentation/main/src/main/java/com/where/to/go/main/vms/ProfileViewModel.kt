package com.where.to.go.main.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.ResponseModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.models.User
import com.where.to.go.internet.plugins.TokenManager
import kotlinx.coroutines.launch
import okhttp3.Response
import kotlin.math.log

class ProfileViewModel: ViewModel() {
    private val userRetrofit by mutableStateOf(RetrofitClient.userService)

    var loginUser by mutableStateOf<User?>(null)

    fun updateUserData(
        newUser: User,
        callback: UserDataChangedCallback
    ) {
        viewModelScope.launch {
            val token = TokenManager.getToken()
            val responseUser =
                UserUseCase()
                    .findUser(
                        RestorePasswordModel(
                            email = loginUser?.email ?: throw IllegalArgumentException("Cannot get email from logend user")
                        )
                    )

            val responseEdit = userRetrofit.editUser(
                authToken = token,
                user = loginUser ?: throw IllegalArgumentException("current user has no User()... LAMO (${loginUser})"),
                id = loginUser?.id ?: throw IllegalArgumentException("current user has no ID... LMAO (${loginUser?.id})")
            )

            val responseGetUser = userRetrofit.getUser(
                loginUser?.id ?: throw IllegalArgumentException("current user has no ID... LMAO (${loginUser?.id})")
            )

            if (!responseUser.isSuccessful) {
                callback.onError("""
                    [updateUserData]: 
                    ----- Message: ${responseUser.message()}
                    ----- ErrorBody: ${responseUser.errorBody()}
                    ----- Raw: ${responseUser.raw()}
                    ----- Code: ${responseUser.code()}
                """.trimIndent())
                return@launch
            }

            Log.e("TAG", "updateUserData userToken: $token", )

            if (!responseEdit.isSuccessful) {
                callback.onError("""
                    [updateUserData] - responseEdit: 
                    ----- Message: ${responseEdit.message()}
                    ----- ErrorBody: ${responseEdit.errorBody()}
                    ----- Raw: ${responseEdit.raw()}
                    ----- Code: ${responseEdit.code()}
                """.trimIndent())
                return@launch
            }



            if (!responseGetUser.isSuccessful) {
                callback.onError("""
                    [updateUserData] - getUser: 
                    ----- Message: ${responseUser.message()}
                    ----- ErrorBody: ${responseUser.errorBody()}
                    ----- Raw ${responseUser.raw()}
                """.trimIndent())
                return@launch
            }

            Log.e("TAG", "updateUserData responseUser.body(): ${responseUser.body()}", )
            loginUser = responseUser.body()
            callback.onSuccess("YESSS")

        }

    }

}

interface UserDataChangedCallback {
    fun onError(msg: String)
    fun onSuccess(msg: String)
}