package com.where.to.go.internet.servers

import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.ConfirmCodeModel
import com.where.to.go.internet.models.ResetPasswordModel
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.models.User
import com.where.to.go.internet.plugins.TokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserServer {
    companion object{
        fun findUser(
            userUseCase: UserUseCase,
            model: AuthResponseModel,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (User) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = userUseCase.findUser(model)
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
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


    }
}