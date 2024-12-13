package com.where.to.go.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.where.to.go.main.fragment.RecommendsFragment

@Composable
fun MainScreenNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.RecommendedScreen.route,
    ) {

        composable(
            route = Screen.RecommendedScreen.route,
        ) {
            RecommendsFragment()
        }

    }
}