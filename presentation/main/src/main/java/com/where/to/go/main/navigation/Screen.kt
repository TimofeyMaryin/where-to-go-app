package com.where.to.go.main.navigation

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.offset
import com.where.to.go.main.R

sealed class Screen(val route: String, val titleRes: Int) {
    data object RecommendedScreen : Screen("recommended-screen", R.string.top_bar_recommend)
    data object ProfileScreen : Screen("profile-screen", R.string.top_bar_profile)
    data object EditProfileScreen : Screen("edit-profile-screen", R.string.top_bar_edit_profile)
    data object FavoritePartyScreen : Screen("favorite-party-screen", R.string.top_bar_favorite)
    data object SchedulePartyScreen : Screen("schedule-party-screen", R.string.top_bar_schedule)
    data object ManagePartyScreen : Screen("manage-party-screen", R.string.top_bar_manage_party)
    data object SettingsScreen : Screen("settings-screen", R.string.top_bar_settings)
    data object PartyScreen : Screen("party-screen", R.string.top_bar_party)
}