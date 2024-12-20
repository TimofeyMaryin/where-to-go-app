package com.where.to.go.main.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.User
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.utils.RecommendTape
import kotlinx.coroutines.launch

class RecommendedViewModel: ViewModel() {
    private val userUseCase = UserUseCase()

    var loginUser by mutableStateOf<User?>(null)

    var recommendedTapeState by mutableStateOf(RecommendTape.VERTICAL)
        private set
    val changeRecommendedTapeState = { recommendedTapeState = if (recommendedTapeState == RecommendTape.VERTICAL) RecommendTape.HORIZONTAL else RecommendTape.VERTICAL }

    // Test
    val categories = mutableStateListOf(false, false, false, false, false, false, false, false)


    init {
        viewModelScope.launch {
            // loginUser = userUseCase.getUser(1).body()
            Log.e("TAG", "ViewModel init: $loginUser", )
        }
    }
}