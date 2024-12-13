package com.where.to.go.main.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.where.to.go.main.fragment.FavoritePartyFragment
import com.where.to.go.main.fragment.ProfileFragment
import com.where.to.go.main.fragment.RecommendsFragment
import com.where.to.go.main.fragment.SchedulePartyFragment
import com.where.to.go.main.utils.AnimateFragmentContainer
import com.where.to.go.main.utils.FragmentContainer
import com.where.to.go.main.vms.RecommendedViewModel


@Composable
fun AppNavigation(
    viewModel: RecommendedViewModel,
) {
    val navController = rememberNavController()

    FragmentContainer(navController = navController, viewModel) {
        NavHost(
            navController = navController,
            startDestination = Screen.RecommendedScreen.route,
        ) {
            composable(
                route = Screen.RecommendedScreen.route,
            ) {
                AnimateFragmentContainer(enable = viewModel.isCurrentNavDestination.invoke(Screen.RecommendedScreen.route)) {
                    RecommendsFragment(viewModel)
                }
            }

            composable(
                route = Screen.SchedulePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = viewModel.isCurrentNavDestination.invoke(Screen.SchedulePartyScreen.route)) {
                    SchedulePartyFragment(navController = navController, viewModel = viewModel)
                }
            }

            composable(
                route = Screen.FavoritePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = viewModel.isCurrentNavDestination.invoke(Screen.FavoritePartyScreen.route)) {
                    FavoritePartyFragment(navController = navController, viewModel = viewModel)
                }
            }

            composable(
                route = Screen.ProfileScreen.route
            ) {
                AnimateFragmentContainer(enable = viewModel.isCurrentNavDestination.invoke(Screen.ProfileScreen.route)) {
                    ProfileFragment(navController = navController, viewModel = viewModel)
                }
            }

        }

    }
}