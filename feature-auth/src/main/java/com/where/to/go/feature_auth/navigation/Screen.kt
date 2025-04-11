package com.where.to.go.feature_auth.navigation

sealed class Screen (val route: String) {
    data object StartScreen : Screen("start-screen")
    data object AuthScreen : Screen("auth-screen")
    data object LoginScreen : Screen("login-screen")
    data object RestoreScreen : Screen("restore-screen")
    data object VerificationScreen : Screen("verification-screen")
    data object ResetPasswordScreen : Screen("reset-password--screen")
}