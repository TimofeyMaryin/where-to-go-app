package com.where.to.go.feature_recommends

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.launch

var partyFilters by mutableStateOf(false)
enum class FiltersState(val padding: Dp){
    Active(110.dp),
    Inactive(900.dp)
}
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun RecommendsFragment(
    rViewModel: RecommendedViewModel,
    pViewModel: PartyViewModel,
    navigateViewModel: NavigationViewModel
) {
    val categories = remember { mutableStateListOf(false, false, false, false) }
    val context = LocalContext.current
    val density = LocalDensity.current
    var search by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    BackHandler {

    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 30.dp, start = offset, end = offset),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        CustomSearchView(
            hint = "Поиск",
            value = search,
            onValueChange = { search = it },
            onFiltersClick = {
                toggleFilters(navigateViewModel, !partyFilters)
                if(!partyFilters) keyboardController?.hide()
            },
            onSearchClick = {
                // TODO Search
            }
        )
        LazyRow(
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 20.dp)
        ) {
            items(categories.size) { index ->
                CategoryToggle(
                    isChecked = categories[index],
                    onToggle = { newState ->
                        categories[index] = newState
                    },
                    label = "Category ${index + 1}"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = "Рекомендованные",
                modifier = Modifier,
                weight = TextWeight.MEDIUM,
                size = TextSize.TITLE,
            )

            SelectedTapeButton(status = rViewModel.recommendedTapeState) {
                rViewModel.changeRecommendedTapeState()
                Log.e("TESTASG", "${rViewModel.recommendedTapeState}")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))


        RecommendedTape(viewModel = rViewModel) {
            pViewModel.party = it
            navigateViewModel.navigate(Screen.PartyScreen.route)
        }

    }
    val offsetY = remember { Animatable(FiltersState.Inactive.padding.value) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .offset(y = offsetY.value.dp)
            .fillMaxHeight()
            .padding(bottom = FiltersState.Active.padding)
            .fillMaxWidth()
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    val deltaDp = with(density) { delta.toDp() }
                    val newOffset = (offsetY.value.dp + deltaDp).coerceAtLeast(FiltersState.Active.padding)
                    coroutineScope.launch {
                        offsetY.snapTo(newOffset.value)
                    }

                    val closeThreshold = FiltersState.Active.padding + 100.dp
                    if (newOffset >= closeThreshold) {
                        toggleFilters(navigateViewModel, false)
                        coroutineScope.launch {
                            offsetY.animateTo(FiltersState.Inactive.padding.value, tween(300))
                            keyboardController?.hide()
                        }
                    }
                },
                onDragStopped = {
                    if (offsetY.value < FiltersState.Active.padding.value + 100) {
                        coroutineScope.launch {
                            offsetY.animateTo(FiltersState.Active.padding.value, tween(300))
                        }
                    }
                }
            )
    ) {
        PartyFilters { date, price, guestsCount ->
            rViewModel.dateRange = mutableStateOf(date)
            rViewModel.priceRange = mutableStateOf(price.first.toInt() to  price.second.toInt())
            rViewModel.guestsRange = mutableStateOf(guestsCount.first.toInt() to  guestsCount.second.toInt())
            rViewModel.applyFilters()
        }
    }

    LaunchedEffect(partyFilters) {
        val target = if (partyFilters) FiltersState.Active.padding.value else FiltersState.Inactive.padding.value
        offsetY.animateTo(target, animationSpec = tween(300))
    }
}

private fun toggleFilters(navigateViewModel: NavigationViewModel, newState: Boolean) {
    partyFilters = newState
    navigateViewModel.bottomNavState = BottomNavState(
        if (newState) colorBg else colorContainerBg,
        !newState
    )
}

@Composable
private fun SelectedTapeButton(
    status: RecommendTape,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = status == RecommendTape.VERTICAL,
            enter = slideInVertically(tween(400)) + fadeIn(tween(400)),
            exit = slideOutVertically(tween(400)) + fadeOut(tween(400))
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_vertical_tape),
                    contentDescription = null,
                    tint = colorGray,
                    modifier = Modifier.size(30.dp)
                )
            }
        }


        AnimatedVisibility(
            visible = status == RecommendTape.HORIZONTAL,
            enter = slideInVertically(tween(400)) + fadeIn(tween(400)),
            exit = slideOutVertically(tween(400)) + fadeOut(tween(400))
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_horizontal_tape),
                    contentDescription = null,
                    tint = colorGray
                )
            }
        }
    }

}

@Composable
private fun RecommendedTape(
    viewModel: RecommendedViewModel,
    onSelectedCurrentItem: (Party) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = viewModel.recommendedTapeState,
            animationSpec = tween(
                durationMillis = 600,
                easing = FastOutSlowInEasing
            ),
            label = "TapeCrossfade"
        ) { tapeState ->
            when (tapeState) {
                RecommendTape.HORIZONTAL -> {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(viewModel.partyList.value.size) { index ->
                            LargePartyView(viewModel.partyList.value[index]) {
                                onSelectedCurrentItem(it)
                            }
                        }

                    }
                }
                RecommendTape.VERTICAL -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(viewModel.partyList.value.size) { index ->
                            PartyView(
                                viewModel.partyList.value[index]
                            ) {
                                onSelectedCurrentItem(it)
                            }
                        }
                        item{
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
    }
}
