package com.where.to.go.auth

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.where.to.go.auth.navigation.AppNavigation
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.component.AppText
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.WhereToGoApplicationTheme
import com.where.to.go.component.values.colorBg
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.UserUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope


@AndroidEntryPoint
class AuthActivity: ComponentActivity(), CoroutineScope by MainScope()  {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var authUseCase: AuthUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authUseCase = AuthUseCase()
        val userUseCase = UserUseCase()
        TokenManager.init(this)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
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


    }
}