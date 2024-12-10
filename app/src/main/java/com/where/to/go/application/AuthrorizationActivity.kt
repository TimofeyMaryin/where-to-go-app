package com.where.to.go.application

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.where.to.go.application.ui.theme.WhereToGoApplicationTheme
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class AuthorizationActivity : ComponentActivity() {
    private lateinit var authUseCase: AuthUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authUseCase = AuthUseCase()

        enableEdgeToEdge()

        setContent { AuthorizationScreen(authUseCase = authUseCase) }
    }
}

@Composable
fun AuthorizationScreen(authUseCase: AuthUseCase) {
    var isLoginScreen by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }
    var responseMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = { isLoginScreen = true }) {
                                Text(text = "Логин")
                            }
                            Button(onClick = { isLoginScreen = false }) {
                                Text(text = "Регистрация")
                            }
                        }

                        if (isLoginScreen) {
                            LoginInterface(
                                onLogin = { email, password ->
                                    handleLogin(
                                        context = context,
                                        authUseCase = authUseCase,
                                        email = email,
                                        password = password,
                                        coroutineScope = coroutineScope,
                                        onLoading = { isLoading = it },
                                        onResult = { responseMessage = it }
                                    )
                                }
                            )
                        } else {
                            SignupInterface(
                                onSignup = { email, password ->
                                    handleSignup(
                                        authUseCase = authUseCase,
                                        email = email,
                                        password = password,
                                        coroutineScope = coroutineScope,
                                        onLoading = { isLoading = it },
                                        onResult = { responseMessage = it }
                                    )
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun LoginInterface(onLogin: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Вход")
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onLogin(email, password) }) {
            Text(text = "Войти")
        }
    }
}

@Composable
fun SignupInterface(onSignup: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Регистрация")
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onSignup(email, password) }) {
            Text(text = "Создать аккаунт")
        }
    }
}

fun handleLogin(
    context: Context,
    authUseCase: AuthUseCase,
    email: String,
    password: String,
    coroutineScope: CoroutineScope,
    onLoading: (Boolean) -> Unit,
    onResult: (String) -> Unit

    ) {
    coroutineScope.launch {
        onLoading(true)
        try {
            val response = authUseCase.login(AuthRequestModel(email = email, phone = "", password = password, name = ""))
            if (response.isSuccessful) {
                val token = response.body()?.token ?: "Токен отсутствует"
                onResult("Успешный вход: $token")
                //TODO save token
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            } else {
                onResult("Ошибка: ${response.message()}")
            }
        } catch (e: Exception) {
            onResult("Ошибка: ${e.message}")
        } finally {
            onLoading(false)
        }
    }
}

// Функция для обработки регистрации
fun handleSignup(
    authUseCase: AuthUseCase,
    email: String,
    password: String,
    coroutineScope: CoroutineScope,
    onLoading: (Boolean) -> Unit,
    onResult: (String) -> Unit
) {
    coroutineScope.launch {
        onLoading(true)
        try {
            val response = authUseCase.signup(AuthRequestModel(email = email, phone = email, password = password, name = ""))
            if (response.isSuccessful) {
                onResult("Reponse: " + response.message())


            } else {
                onResult("Ошибка: ${response.message()}")
            }
        } catch (e: Exception) {
            onResult("Ошибка: ${e.message}")
        } finally {
            onLoading(false)
        }
    }
}