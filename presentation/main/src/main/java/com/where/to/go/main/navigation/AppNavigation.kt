package com.where.to.go.main.navigation

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.main.fragment.FavoritePartyFragment
import com.where.to.go.main.fragment.ImageEditorFragment
import com.where.to.go.main.fragment.ProfileFragment
import com.where.to.go.main.fragment.RecommendsFragment
import com.where.to.go.main.fragment.SchedulePartyFragment
import com.where.to.go.main.utils.AnimateFragmentContainer
import com.where.to.go.main.utils.FragmentContainer
import com.where.to.go.main.vms.ImageEditorViewModel
import com.where.to.go.main.vms.NavigationViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel


@Composable
fun AppNavigation(
    recommendsViewModel: RecommendedViewModel,
    profileViewModel: ProfileViewModel,
    editorViewModel: ImageEditorViewModel
) {
    val navController = rememberNavController()
    val navigationViewModel: NavigationViewModel = viewModel()
    val userUseCase = UserUseCase()

    FragmentContainer(navController = navController, navigationViewModel) {
        NavHost(
            navController = navController,
            startDestination = Screen.RecommendedScreen.route,
        ) {
            composable(
                route = Screen.RecommendedScreen.route,
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.RecommendedScreen.route)) {
                    RecommendsFragment(recommendsViewModel)
                }
            }

            composable(
                route = Screen.SchedulePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.SchedulePartyScreen.route)) {
                    SchedulePartyFragment(navController = navController, viewModel = recommendsViewModel)
                }
            }

            composable(
                route = Screen.FavoritePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.FavoritePartyScreen.route)) {
                    FavoritePartyFragment(navController = navController, viewModel = recommendsViewModel)
                }
            }

            composable(
                route = Screen.ProfileScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.ProfileScreen.route)) {
                    ProfileFragment(
                        navController = navController,
                        viewModel = profileViewModel,
                        navigationViewModel = navigationViewModel,
                        userUseCase = userUseCase)
                }
            }

            composable(
                route = Screen.ImageEditorScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.ImageEditorScreen.route)) {
                    ImageEditorFragment(editorViewModel)
                }
            }

        }

    }
}