package com.where.to.go.main.fragment

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.colorGray
import com.where.to.go.main.R
import com.where.to.go.main.utils.RecommendTape
import com.where.to.go.main.vms.RecommendedViewModel


@Composable
fun RecommendsFragment(
    viewModel: RecommendedViewModel,
) {
    val categories = remember { mutableStateListOf(false, false, false, false) }
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }

    BackHandler {
        
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        CustomSearchView(
            hint = "Поиск",
            value = search,
            onValueChange = { search = it },
            onSearchClick = {
                // TODO Search
            }
        )
        /*LazyRow(
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 20.dp)
        ) {
            items(categories.size) { index ->
                CategoryToggle(
                    isChecked = categories[index],
                    onToggle = { newState ->
                        categories[index] = newState
                        Log.e("ONTOGGLE", categories[index].toString())
                    },
                    label = "Category ${index + 1}"
                )
            }
        }*/
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = "Рекомендованные",
                modifier = Modifier,
                weight = TextWeight.REGULAR,
                size = TextSize.TITLE_MEDIUM,
            )

            SelectedTapeButton(status = viewModel.recommendedTapeState) {
                viewModel.changeRecommendedTapeState()
                Log.e("TESTASG", "${viewModel.recommendedTapeState}")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))


        RecommendedTape(viewModel = viewModel) {
            Toast.makeText(context, "Hello GUES", Toast.LENGTH_SHORT).show()
        }

    }

}

@Composable
private fun SelectedTapeButton(
    status: RecommendTape,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(50.dp),
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
    onSelectedCurrentItem: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = viewModel.recommendedTapeState == RecommendTape.HORIZONTAL,
            enter = fadeIn(tween(800)),
            exit = fadeOut(tween(400))
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                viewModel.recommendedParties?.let {
                    items(it.size) { index ->
                        LargePartyView(
                            viewModel.recommendedParties!![index],
                            backgroundImageResId = com.where.to.go.component.R.drawable.test
                        ) {
                            onSelectedCurrentItem()
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = viewModel.recommendedTapeState == RecommendTape.VERTICAL,
            enter = fadeIn(tween(400)),
            exit = fadeOut(tween(400))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.categories.size) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            colorContainerBg
                        ))
                }
            }
        }

    }

}