package com.where.to.go.auth.navigation

sealed class Screen (val route: String) {
    data object StartScreen : Screen("start-screen")
    data object AuthScreen : Screen("auth-screen")
    data object LoginScreen : Screen("login-screen")
}