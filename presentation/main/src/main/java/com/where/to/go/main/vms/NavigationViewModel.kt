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

    var navController: NavController? = null

    val isCurrentNavDestination: (String) -> Boolean = { it == currentNavDestination }

    fun navigate(dest: String, navController: NavController? = this.navController) {
        if(navController != null) {
            currentNavDestination = dest
            navController.navigate(dest)
        }else{
            Log.e("TAG", "NavController is null")
        }

    }
}