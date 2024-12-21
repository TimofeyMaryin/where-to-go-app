package com.where.to.go.main.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.RequestState
import com.where.to.go.internet.models.ResponseModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.models.User
import com.where.to.go.internet.plugins.TokenManager
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class EditProfileViewModel: ViewModel() {
    var loginUser by mutableStateOf<User?>(null)

    private val userUseCase = UserUseCase()
    private val tokenManager = TokenManager

    val uploadAvatarState = MutableLiveData<RequestState<ResponseModel>>()

    fun uploadAvatar(file: MultipartBody.Part) {
        uploadAvatarState.value = RequestState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = userUseCase.uploadAvatar(loginUser!!.id, file, tokenManager.getHeaderToken())
                if (response.isSuccessful) {
                    uploadAvatarState.value = RequestState(data = response.body())
                } else {
                    uploadAvatarState.value = RequestState(error = "Ошибка: ${response.raw()}")
                    Log.e("AVATAR", response.raw().toString())
                }
            } catch (e: Exception) {
                Log.e("AVATAR", e.message.toString())
                uploadAvatarState.value = RequestState(error = "Ошибка: ${e.message} ${loginUser == null}")
            }
        }
    }
}