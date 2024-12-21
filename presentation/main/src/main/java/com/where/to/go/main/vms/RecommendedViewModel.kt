package com.where.to.go.main.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.where.to.go.internet.cases.PartyUseCase
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.Party
import com.where.to.go.internet.models.RequestState
import com.where.to.go.internet.models.User
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.utils.RecommendTape
import kotlinx.coroutines.launch

class RecommendedViewModel: ViewModel() {
    private val userUseCase = UserUseCase()
    private val partyUseCase = PartyUseCase()

    var loginUser by mutableStateOf<User?>(null)
    var navController: NavController? = null

    var recommendedTapeState by mutableStateOf(RecommendTape.VERTICAL)
        private set

    fun changeRecommendedTapeState(){
        recommendedTapeState = if (recommendedTapeState == RecommendTape.VERTICAL) RecommendTape.HORIZONTAL else RecommendTape.VERTICAL
    }

    // Test
    val categories = mutableStateListOf(false, false, false, false, false, false, false, false)

    var recommendedParties: List<Party>? = null

    init {
        viewModelScope.launch {
            //recommendedParties = getParties()
            // loginUser = userUseCase.getUser(1).body()
            Log.e("TAG", "ViewModel init: $loginUser", )
        }
    }
    val recommendsState = MutableLiveData<RequestState<List<Party>>>()

    suspend fun getParties(): List<Party>? {
        recommendsState.value = RequestState(isLoading = true)
        return try {
            val response = partyUseCase.getAllParties()
            if (response.isSuccessful) {
                val parties = response.body()
                recommendsState.value = RequestState(data = parties)
                parties
            } else {
                recommendsState.value = RequestState(error = "Ошибка: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            recommendsState.value = RequestState(error = "Ошибка: ${e.message}")
            null
        }
    }

    fun navigate(dest: String) {
        navController?.navigate(dest) ?: throw IllegalArgumentException("nav controller has not init")
    }
}