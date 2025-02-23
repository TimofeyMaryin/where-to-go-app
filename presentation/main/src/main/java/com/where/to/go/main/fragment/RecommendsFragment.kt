package com.where.to.go.main.fragment

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.where.to.go.component.AppText
import com.where.to.go.component.CategoryToggle
import com.where.to.go.component.CustomSearchView
import com.where.to.go.component.LargePartyView
import com.where.to.go.component.PartyView
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorGray
import com.where.to.go.component.values.offset
import com.where.to.go.internet.models.Party
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.utils.RecommendTape
import com.where.to.go.main.vms.NavigationViewModel
import com.where.to.go.main.vms.PartyViewModel
import com.where.to.go.main.vms.RecommendedViewModel


@Composable
fun RecommendsFragment(
    rViewModel: RecommendedViewModel,
    pViewModel: PartyViewModel,
    navigateViewModel: NavigationViewModel
) {
    val categories = remember { mutableStateListOf(false, false, false, false) }
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }

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
    Box(modifier = Modifier.fillMaxSize()){

    }

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
                        items(viewModel.partyList.size) { index ->
                            LargePartyView(viewModel.partyList[index]) {
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
                        items(viewModel.partyList.size) { index ->
                            PartyView(
                                viewModel.partyList[index]
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