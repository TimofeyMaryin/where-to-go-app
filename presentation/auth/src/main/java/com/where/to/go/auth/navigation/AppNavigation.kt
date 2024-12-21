package com.where.to.go.auth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.where.to.go.auth.screen.LoginScreen
import com.where.to.go.auth.screen.AuthScreen
import com.where.to.go.auth.screen.ResetPasswordScreen
import com.where.to.go.auth.screen.RestoreScreen
import com.where.to.go.auth.screen.StartScreen
import com.where.to.go.auth.screen.VerificationScreen
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.cases.UserUseCase

@Composable
fun AppNavigation(
    viewModel: AuthViewModel,
    authUseCase: AuthUseCase,
    userUseCase: UserUseCase
) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route,
    ) {


        this.composable(
            route = Screen.StartScreen.route,

        ) {
            StartScreen(navController = navController, viewModel = viewModel)
        }

        this.composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(navController = navController, viewModel = viewModel, authUseCase = authUseCase, userUseCase = userUseCase)
        }

        this.composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController, viewModel = viewModel)
        }

        this.composable(
            route = Screen.RestoreScreen.route
        ) {
            RestoreScreen(navController = navController, viewModel = viewModel, authUseCase = authUseCase)
        }

        this.composable(
            route = Screen.VerificationScreen.route
        ) {
            VerificationScreen(navController = navController, viewModel = viewModel, authUseCase = authUseCase)
        }
        this.composable(
            route = Screen.ResetPasswordScreen.route
        ) {
            ResetPasswordScreen(navController = navController, viewModel = viewModel)
        }

    }

}