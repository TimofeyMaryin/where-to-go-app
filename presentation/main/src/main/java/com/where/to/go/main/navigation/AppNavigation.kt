package com.where.to.go.main.navigation

import android.os.Build

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.UserRole
import com.where.to.go.main.factory.NavigationViewModelFactory
import com.where.to.go.main.fragment.EditProfileFragment
import com.where.to.go.main.fragment.FavoritePartyFragment
import com.where.to.go.main.fragment.ManagePartyFragment
import com.where.to.go.main.fragment.PartyFragment
import com.where.to.go.main.fragment.ProfileFragment
import com.where.to.go.main.fragment.RecommendsFragment
import com.where.to.go.main.fragment.SchedulePartyFragment
import com.where.to.go.main.fragment.SettingsFragment
import com.where.to.go.main.utils.AnimateFragmentContainer
import com.where.to.go.main.utils.FragmentContainer
import com.where.to.go.main.vms.EditProfileViewModel
import com.where.to.go.main.vms.NavigationViewModel
import com.where.to.go.main.vms.PartyViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel
import com.where.to.go.main.vms.ScheduleViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    recommendsViewModel: RecommendedViewModel,  
    scheduleViewModel: ScheduleViewModel,
    partyViewModel: PartyViewModel,
    profileViewModel: ProfileViewModel,
    editorViewModel: EditProfileViewModel
) {
    val navController = rememberNavController()
    val role = UserRole.fromId(profileViewModel.loginUser?.role ?: 1)
    val navigationViewModel: NavigationViewModel = viewModel(factory = NavigationViewModelFactory(role))
    navigationViewModel.navController = navController
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
                    RecommendsFragment(navigateViewModel = navigationViewModel,
                        rViewModel = recommendsViewModel,
                        pViewModel = partyViewModel)
                }
            }

            composable(
                route = Screen.SchedulePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.SchedulePartyScreen.route)) {
                    when(navigationViewModel.userRole){
                        UserRole.GUEST -> SchedulePartyFragment(navigateViewModel = navigationViewModel, scheduleViewModel = scheduleViewModel);
                        UserRole.ORGANIZER -> ManagePartyFragment(navigateViewModel = navigationViewModel, scheduleViewModel = scheduleViewModel)
                    }

                }
            }

            composable(
                route = Screen.FavoritePartyScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.FavoritePartyScreen.route)) {
                    FavoritePartyFragment(viewModel = recommendsViewModel)
                }
            }

            composable(
                route = Screen.ProfileScreen.route
            ) {

                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.ProfileScreen.route), 0) {
                    ProfileFragment(
                        viewModel = profileViewModel,
                        userUseCase = userUseCase, navigationViewModel = navigationViewModel)
                }
            }

            composable(
                route = Screen.EditProfileScreen.route
            ) {
                AnimateFragmentContainer(enable = navigationViewModel.isCurrentNavDestination.invoke(Screen.EditProfileScreen.route), 10000) {
                    EditProfileFragment(profileViewModel, editorViewModel)
                }
            }

            composable(
                route = Screen.SettingsScreen.route
            ) {
                AnimateFragmentContainer(
                    enable = navigationViewModel.isCurrentNavDestination.invoke(
                        Screen.SettingsScreen.route
                    )
                ) {
                    SettingsFragment(navigationViewModel = navigationViewModel)
                }
            }

            composable(
                route = Screen.PartyScreen.route
            ) {
                AnimateFragmentContainer(
                    enable = navigationViewModel.isCurrentNavDestination.invoke(
                        Screen.PartyScreen.route
                    )
                ) {
                    PartyFragment(
                        navigationViewModel = navigationViewModel,
                        viewModel = partyViewModel)
                }
            }

        }

    }
}