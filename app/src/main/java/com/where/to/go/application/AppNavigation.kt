package com.where.to.go.application

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.where.to.go.application.container.HomeContainer

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navViewModel: MainNavViewModel = hiltViewModel(),
    recommendsViewModel: RecommendedViewModel = hiltViewModel(),
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
    partyViewModel: PartyViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    editorViewModel: EditProfileViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val userRole by profileViewModel.userRole.collectAsState()
    LaunchedEffect(navController) {
        navViewModel.navigationEvents.collect { route ->
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "home_container",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("home_container") {
            HomeContainer(
                navViewModel = navViewModel,
                recommendsViewModel = recommendsViewModel,
                scheduleViewModel = scheduleViewModel,
                profileViewModel = profileViewModel,
                userRole = userRole
            )
        }
        composable(Screen.EditProfileScreen.route) {
            FullScreenContainer {
                EditProfileScreen(
                    profileViewModel = profileViewModel,
                    editorViewModel = editorViewModel,
                    navViewModel = navViewModel
                )
            }
        }
        composable(Screen.SettingsScreen.route) {
            FullScreenContainer {
                SettingsScreen(navViewModel = navViewModel)
            }
        }
        composable(Screen.PartyScreen.route) {
            FullScreenContainer {
                PartyScreen(
                    viewModel = partyViewModel,
                    navViewModel = navViewModel
                )
            }
        }
    }
}