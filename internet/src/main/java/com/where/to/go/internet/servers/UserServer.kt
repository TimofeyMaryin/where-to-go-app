package com.where.to.go.internet.servers

import android.util.Log
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
import okhttp3.MultipartBody

interface UserServerInterface{
    fun findUser(
        userUseCase: UserUseCase,
        model: RestorePasswordModel,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (User) -> Unit,
        onError: (String) -> Unit
    )

    fun editUser(
        userUseCase: UserUseCase,
        model: User,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (User) -> Unit,
        onError: (String) -> Unit
    )

    fun uploadAvatar(
        userUseCase: UserUseCase,
        file: MultipartBody.Part,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun deleteUser(
        userUseCase: UserUseCase,
        id: Int,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun editUser(
        userUseCase: UserUseCase,
        id: Int,
        user: User,
        tokenManager: TokenManager,
        coroutineScope: CoroutineScope,
        onLoading: (Boolean) -> Unit,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    )
}

class UserServer {
    companion object : UserServerInterface{
        override fun findUser(
            userUseCase: UserUseCase,
            model: RestorePasswordModel,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (User) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                Log.e("TAG", "findUser: start", )
                onLoading(true)
                try {
                    Log.e("TAG", "findUser: after try", )
                    val response = userUseCase.findUser(model)
                    Log.e("TAG", "findUser: after response", )
                    Log.e("TAG", "findUser: ${response.isSuccessful}", )
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("Ошибка: ${response.raw()}")
                    }
                } catch (e: Exception) {
                    Log.e("TAG", "findUser error:$e ", )
                    onError(e.message.toString())
                } finally {
                    Log.e("TAG", "findUser finally: false ;(", )
                    onLoading(false)
                }
            }
        }


        override fun editUser(
            userUseCase: UserUseCase,
            model: User,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (User) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val user = userUseCase.findUser(RestorePasswordModel(model.email!!))
                    if(user.body() != null){
                        val response = userUseCase.editUser(user.body()!!.id, model, tokenManager.getToken())
                        if (response.isSuccessful) {
                            onResult(response.body()!!)
                        } else {
                            onError("Ошибка: ${response.raw()}")
                        }
                    }else {
                        onError("User not found")
                    }
                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }
        }

        override fun editUser(
            userUseCase: UserUseCase,
            id: Int,
            user: User,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val request = userUseCase.editUser(id, user, tokenManager.getToken())
                    if (request.isSuccessful) {
                        onResult(request.body()!!.toString())
                    } else {
                        onError("Ошибка: ${request.raw()}")
                    }

                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }        }

        override fun uploadAvatar(
            userUseCase: UserUseCase,
            file: MultipartBody.Part,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val user = userUseCase.findUser(RestorePasswordModel(tokenManager.getEmail()))
                    if(user.body() != null){
                        val response = userUseCase.uploadAvatar(user.body()!!.id, file, tokenManager.getToken())
                        if (response.isSuccessful) {
                            onResult(response.body()!!)
                        } else {
                            onError("Ошибка: ${response.raw()}")
                        }
                    }else {
                        onError("User not found")
                    }

                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }
        }

        override fun deleteUser(
            userUseCase: UserUseCase,
            id: Int,
            tokenManager: TokenManager,
            coroutineScope: CoroutineScope,
            onLoading: (Boolean) -> Unit,
            onResult: (String) -> Unit,
            onError: (String) -> Unit
        ) {
            coroutineScope.launch {
                onLoading(true)
                try {
                    val response = userUseCase.deleteUser(id, tokenManager.getToken())
                    if (response.isSuccessful) {
                        onResult(response.body()!!)
                    } else {
                        onError("User not found")
                    }

                } catch (e: Exception) {
                    onError(e.message.toString())
                } finally {
                    onLoading(false)
                }
            }        }
    }
}