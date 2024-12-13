package com.where.to.go.main.navigation

sealed class Screen(val route: String) {
    data object RecommendedScreen : Screen("recommended-screen")
    data object ProfileScreen : Screen("profile-screen")
    data object SchedulePartyScreen : Screen("schedule-party-screen")
}