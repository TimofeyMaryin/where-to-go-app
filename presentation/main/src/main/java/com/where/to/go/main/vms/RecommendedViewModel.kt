package com.where.to.go.main.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class RecommendedViewModel: ViewModel() {
    var currentNavDestination by mutableStateOf("")

    val isCurrentNavDestination: (String) -> Boolean = { it == currentNavDestination }
    fun navigate(navController: NavController, dest: String) {
        currentNavDestination = dest
        navController.navigate(dest)
    }

}