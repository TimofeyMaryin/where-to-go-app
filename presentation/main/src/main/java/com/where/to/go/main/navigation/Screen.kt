package com.where.to.go.main.navigation

sealed class Screen(val route: String) {
    data object RecommendedScreen : Screen("recommended-screen")
    data object ProfileScreen : Screen("profile-screen")
    data object FavoritePartyScreen : Screen("favorite-party-screen")
    data object SchedulePartyScreen : Screen("schedule-party-screen")
    data object ImageEditorScreen : Screen("image-editor-screen")
    data object EditProfileScreen : Screen("edit-profile-profile")
}