package com.where.to.go.main.vms

import android.graphics.Insets.add
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.R

data class BottomNavState(
    val color: Color,
    val isOpen: Boolean
)

class NavigationViewModel : ViewModel() {
    private var _bottomNavState by mutableStateOf(BottomNavState(colorContainerBg, true))
    var bottomNavState: BottomNavState
        get() = when (currentNavDestination) {
            Screen.ProfileScreen.route -> BottomNavState(colorBg, true)
            Screen.EditProfileScreen.route -> BottomNavState(colorBg, false)
            Screen.PartyScreen.route -> BottomNavState(colorBg, false)
            else -> _bottomNavState
        }
        set(value) {
            _bottomNavState = value
        }

    private val screens: Map<String, Screen> by lazy {
        Screen::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .associateBy { it.route }
    }
    var currentNavDestination by mutableStateOf(Screen.RecommendedScreen.route)
        private set

    var navController: NavController? = null

    val isCurrentNavDestination: (String) -> Boolean = { it == currentNavDestination }

    fun navigate(dest: String, navController: NavController? = this.navController) {
        if (navController != null) {
            currentNavDestination = dest
            navController.navigate(dest)
        } else {
            Log.e("TAG", "NavController is null")
        }
    }

    fun navigateBack(navController: NavController? = this.navController) {
        if (navController != null) {
            val previousDestination = navController.previousBackStackEntry?.destination?.route ?: ""
            if(previousDestination.isEmpty()) return

            currentNavDestination = previousDestination
            navController.navigate(previousDestination)
        } else {
            Log.e("TAG", "NavController is null")
        }
    }



    private fun getScreenByRoute(route: String): Screen? = screens[route]

    fun getCurrentScreenTitle(): Int {
        return getScreenByRoute(currentNavDestination)?.titleRes ?: R.string.error
    }
}