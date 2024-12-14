package com.where.to.go.auth

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.where.to.go.auth.navigation.AppNavigation
import com.where.to.go.internet.plugins.ServerHelper.Companion.updateToken
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.component.AppText
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.WhereToGoApplicationTheme
import com.where.to.go.component.colorBg
import com.where.to.go.internet.RetrofitClient
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope


@AndroidEntryPoint
class AuthActivity: ComponentActivity(), CoroutineScope by MainScope()  {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var authUseCase: AuthUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authUseCase = AuthUseCase()
        val userUseCase = UserUseCase()

        enableEdgeToEdge()
        setContent {
            WhereToGoApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorBg),
                ) {
                    AppNavigation(viewModel = authViewModel, authUseCase = authUseCase, userUseCase)
                    it

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                            AppText(text = "Version: 0.1 - Debug", weight = TextWeight.MEDIUM, size = TextSize.BODY_LARGE)
                        }
                    }

                }
            }
        }

        TokenManager.init(this)
        if(!TokenManager.getToken().isNullOrEmpty()){
            updateToken(
                authUseCase= authUseCase,
                tokenManager= TokenManager,
                coroutineScope = MainScope(),
                onLoading = {},
                onResult = {
                    //Log.e("EGTAG", it)
                    TokenManager.saveToken(it)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                },
                onError = {
                    Log.e("EGTAG", it)
                    // TODO Show error
                })
        }else{
            //Log.e("EGTAG", "No token")
        }
    }
}