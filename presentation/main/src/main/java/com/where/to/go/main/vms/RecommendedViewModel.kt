package com.where.to.go.main.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.where.to.go.internet.cases.PartyUseCase
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.Party
import com.where.to.go.internet.models.RequestState
import com.where.to.go.internet.models.User
import com.where.to.go.main.utils.RecommendTape
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

class RecommendedViewModel: ViewModel() {
    private val userUseCase = UserUseCase()
    private val partyUseCase = PartyUseCase()

    var loginUser by mutableStateOf<User?>(null)

    var recommendedTapeState by mutableStateOf(RecommendTape.VERTICAL)
        private set

    fun changeRecommendedTapeState(){
        recommendedTapeState = if (recommendedTapeState == RecommendTape.VERTICAL) RecommendTape.HORIZONTAL else RecommendTape.VERTICAL
    }
    /*** Test ***/

    val categories = mutableStateListOf(false, false, false, false, false, false, false, false)

    val partiesData = listOf(
        Party(
            id = 1,
            ownerId = 1001,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTRgUPG8RubS8Z3lAG1lGlwJyudTbg6KXoeEyfQHe0QktRH3ahrGyMI0FnK0tNGPuUd0w&usqp=CAU",
            about = "Незабываемая вечеринка на крыше с видом на город!",
            tg = "@RoofParty",
            price = 1500,
            theme = "Neon Nights",
            name = "Ночная тусовка",
            date = "2025-03-01T20:00:00",
            maxGuests = 50,
            status = 1,
            created = "2025-02-21T10:00:00"
        ),
        Party(
            id = 2,
            ownerId = 1002,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmwGQR00tPmGzacLSknci2U1sHXtIWqDbpSNYrPVjsSQjdcCaOaY-u-jwjW3eVVpF3ES8&usqp=CAU",
            about = "Темная вечеринка в стиле gothic.",
            tg = null,
            price = 2000,
            theme = "Gothic Vibes",
            name = "Тьма и тайны",
            date = "2025-03-15T22:00:00",
            maxGuests = 30,
            status = 0,
            created = "2025-02-20T15:30:00"
        ),
        Party(
            id = 3,
            ownerId = 1003,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaZFp_HtcOilLOQIgCyl2yRqCiG4TY-WyYP1RSfeReT_4T3eDOWzh77-kXpd39DBfhnmQ&usqp=CAU",
            about = "Дневная вечеринка у бассейна с коктейлями.",
            tg = "@PoolParty2025",
            price = 1000,
            theme = "Summer Splash",
            name = "Бассейн и солнце",
            date = "2025-06-10T14:00:00",
            maxGuests = 100,
            status = 1,
            created = "2025-02-21T09:15:00"
        ),
        Party(
            id = 4,
            ownerId = 1004,
            image = "https://images.a-a-ah.ru/uploads/list/photo/269/big_concert-2527495_960_720.jpg",
            about = "Закрытая вечеринка для любителей техно.",
            tg = "@TechnoRave",
            price = 2500,
            theme = "Techno Beats",
            name = "Рейв до утра",
            date = "2025-04-20T23:00:00",
            maxGuests = 80,
            status = 1,
            created = "2025-02-19T20:45:00"
        ),
        Party(
            id = 5,
            ownerId = 1005,
            image = "https://img.freepik.com/free-vector/party-crowd-purple-stars-background_1048-7508.jpg",
            about = "Костюмированная вечеринка на Хэллоуин.",
            tg = null,
            price = 1800,
            theme = "Halloween Horror",
            name = "Страшная ночь",
            date = "2025-10-31T19:00:00",
            maxGuests = 60,
            status = 1,
            created = "2025-02-21T12:00:00"
        )
    )
    /*** Test ***/

    var partyList = mutableStateOf(partiesData)
        private set

    var priceRange = mutableStateOf(0 to Int.MAX_VALUE)
    var guestsRange = mutableStateOf(0 to Int.MAX_VALUE)
    var dateRange = mutableStateOf("" to "")

    fun applyFilters() {
        partyList.value = partiesData.filter { party ->
            val priceInRange = party.price in priceRange.value.first..priceRange.value.second
            val guestsInRange = party.maxGuests in guestsRange.value.first..guestsRange.value.second
            val dateInRange = isDateInRange(party.date, dateRange.value.first, dateRange.value.second)

            priceInRange && guestsInRange && dateInRange
        }
    }

    private fun isDateInRange(date: String, startDate: String, endDate: String): Boolean {
        val partyDate = date.toLocalDate()
        val start = startDate.takeIf { it.isNotEmpty() }?.toLocalDate() ?: LocalDate.parse("1970-01-01")
        val end = endDate.takeIf { it.isNotEmpty() }?.toLocalDate() ?: LocalDate.parse("9999-12-31")

        return partyDate in start..end
    }

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
}