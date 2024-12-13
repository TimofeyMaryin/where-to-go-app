package com.where.to.go.main.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.utils.RecommendTape

class RecommendedViewModel: ViewModel() {
    var currentNavDestination by mutableStateOf(Screen.RecommendedScreen.route)

    val isCurrentNavDestination: (String) -> Boolean = { it == currentNavDestination }
    fun navigate(navController: NavController, dest: String) {
        currentNavDestination = dest
        navController.navigate(dest)
    }

    var recommendedTapeState by mutableStateOf(RecommendTape.VERTICAL)
        private set
    val changeRecommendedTapeState = { recommendedTapeState = if (recommendedTapeState == RecommendTape.VERTICAL) RecommendTape.HORIZONTAL else RecommendTape.VERTICAL }


    val categories = mutableStateListOf(false, false, false, false, false, false, false, false)
}