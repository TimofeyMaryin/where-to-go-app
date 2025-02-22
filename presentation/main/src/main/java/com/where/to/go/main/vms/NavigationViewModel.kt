package com.where.to.go.main.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.where.to.go.main.navigation.Screen

class NavigationViewModel : ViewModel() {
    var currentNavDestination by mutableStateOf(Screen.RecommendedScreen.route)
        private set

    var navController: NavController? = null

    val isCurrentNavDestination: (String) -> Boolean = { it == currentNavDestination }

    fun navigate(dest: String, navController: NavController? = this.navController) {
        if (navController != null) {
            currentNavDestination = dest
            Log.e("NAVVV", currentNavDestination)
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

}