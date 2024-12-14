package com.where.to.go.main.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.main.fragment.FavoritePartyFragment
import com.where.to.go.main.fragment.ProfileFragment
import com.where.to.go.main.fragment.RecommendsFragment
import com.where.to.go.main.fragment.SchedulePartyFragment
import com.where.to.go.main.utils.AnimateFragmentContainer
import com.where.to.go.main.utils.FragmentContainer
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel


@Composable
fun AppNavigation(
    recommendsViewModel: RecommendedViewModel,
    profileViewModel: ProfileViewModel,
) {
    val navController = rememberNavController()
    val userUseCase = UserUseCase()
    FragmentContainer(navController = navController, recommendsViewModel) {
        NavHost(
            navController = navController,
            startDestination = Screen.RecommendedScreen.route,
        ) {
            composable(
                route = Screen.RecommendedScreen.route,
            ) {
                AnimateFragmentContainer(enable = recommendsViewModel.isCurrentNavDestination.invoke(Screen.RecommendedScreen.route)) {
                    RecommendsFragment(recommendsViewModel)
                }
            }

            composable(
                route = Screen.SchedulePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = recommendsViewModel.isCurrentNavDestination.invoke(Screen.SchedulePartyScreen.route)) {
                    SchedulePartyFragment(navController = navController, viewModel = recommendsViewModel)
                }
            }

            composable(
                route = Screen.FavoritePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = recommendsViewModel.isCurrentNavDestination.invoke(Screen.FavoritePartyScreen.route)) {
                    FavoritePartyFragment(navController = navController, viewModel = recommendsViewModel)
                }
            }

            composable(
                route = Screen.ProfileScreen.route
            ) {
                AnimateFragmentContainer(enable = recommendsViewModel.isCurrentNavDestination.invoke(Screen.ProfileScreen.route)) {
                    ProfileFragment(navController = navController,
                        viewModel = profileViewModel,
                        userUseCase = userUseCase)
                }
            }

        }

    }
}