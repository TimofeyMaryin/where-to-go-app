package com.where.to.go.application.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.where.to.go.application.NavigationViewModel

@Composable
fun HomeContainer(
    navViewModel: NavigationViewModel) {
    val navController = rememberNavController()
    var hamburgerMenuState by remember { mutableStateOf(false) }
    val bottomNavState by navViewModel.bottomNavState.collectAsState()

    GlobalContainer(
        topBarStart = {
            TopBarStart(
                navigationViewModel = navViewModel,
                onToggle = { hamburgerMenuState = it }
            )
        },
        topBarEnd = { TopBarEnd(navigationViewModel = navViewModel) },
        horizontalOffset = 0.dp
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.RecommendedScreen.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Screen.RecommendedScreen.route) {
                AnimateFragmentContainer(
                    enable = navViewModel.isCurrentNavDestination(Screen.RecommendedScreen.route)
                ) {
                    RecommendsScreen(
                        recommendsViewModel = recommendsViewModel,
                        navViewModel = navViewModel
                    )
                }
            }
            composable(Screen.SchedulePartyScreen.route) {
                AnimateFragmentContainer(
                    enable = navViewModel.isCurrentNavDestination(Screen.SchedulePartyScreen.route)
                ) {
                    when (userRole) {
                        UserRole.GUEST -> SchedulePartyScreen(
                            scheduleViewModel = scheduleViewModel,
                            navViewModel = navViewModel
                        )
                        UserRole.ORGANIZER -> ManagePartyScreen(
                            scheduleViewModel = scheduleViewModel,
                            navViewModel = navViewModel
                        )
                    }
                }
            }
            composable(Screen.FavoritePartyScreen.route) {
                AnimateFragmentContainer(
                    enable = navViewModel.isCurrentNavDestination(Screen.FavoritePartyScreen.route)
                ) {
                    FavoritePartyScreen(
                        viewModel = recommendsViewModel,
                        navViewModel = navViewModel
                    )
                }
            }
            composable(Screen.ProfileScreen.route) {
                AnimateFragmentContainer(
                    enable = navViewModel.isCurrentNavDestination(Screen.ProfileScreen.route)
                ) {
                    ProfileScreen(
                        viewModel = profileViewModel,
                        navViewModel = navViewModel
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (bottomNavState.color != Color.White && bottomNavState.isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.23f)
                            )
                        )
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomMenu(
                viewModel = navViewModel,
                state = bottomNavState,
                navController = navController
            )
        }

        HamburgerMenu(
            enable = hamburgerMenuState,
            timeAnim = 400,
            navController = navController,
            viewModel = navViewModel,
            onStateChange = { hamburgerMenuState = it }
        )
    }
}