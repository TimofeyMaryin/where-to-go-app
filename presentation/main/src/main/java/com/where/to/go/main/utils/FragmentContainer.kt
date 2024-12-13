package com.where.to.go.main.utils

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.component.AppText
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.animatedColorPrimary
import com.where.to.go.component.colorContainerBg
import com.where.to.go.component.colorGray
import com.where.to.go.component.primaryClip
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.vms.RecommendedViewModel

@Composable
fun FragmentContainer(
    navController: NavController,
    viewModel: RecommendedViewModel,
    content: @Composable () -> Unit
) {
    GlobalContainer(
        topBarStart = {

            SquareButton(icon = R.drawable.ic_top_bar_menu) {
                // TODO Show menu
            }

            AppText(
                text = stringResource(
                    id = when (viewModel.currentNavDestination) {
                        Screen.RecommendedScreen.route -> R.string.top_bar_recommend
                        Screen.SchedulePartyScreen.route -> R.string.top_bar_schedule
                        Screen.FavoritePartyScreen.route -> R.string.top_bar_favorite
                        Screen.ProfileScreen.route -> R.string.top_bar_profile
                        else -> R.string.error
                    }
                ),
                weight = TextWeight.REGULAR,
                size = TextSize.TITLE_MEDIUM
            )


        },
        topBarEnd = {
            SquareButton(icon = R.drawable.ic_top_bar_settings) {
                // TODO Settings
            }
        }
    ) {

        content()

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            BottomMenu(navController = navController, viewModel = viewModel,)
        }


    }
}


@Composable
private fun BottomMenu(
    navController: NavController,
    viewModel: RecommendedViewModel,
) {
    Box(
        modifier = Modifier
            .clip(primaryClip())
            .fillMaxWidth()
            .height(70.dp)
            .background(colorContainerBg),
        contentAlignment = Alignment.Center,
    ) {

        Row(
            modifier = Modifier.fillMaxSize(.9f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomMenuItem(
                ic = R.drawable.ic_bottom_bar_rec,
                selected = viewModel.isCurrentNavDestination.invoke(Screen.RecommendedScreen.route)
            ) {
                if (viewModel.currentNavDestination != Screen.RecommendedScreen.route) {
                    viewModel.navigate(navController, Screen.RecommendedScreen.route)
                }
            }

            BottomMenuItem(
                ic = R.drawable.ic_bottom_bar_calendar,
                selected = viewModel.isCurrentNavDestination.invoke(Screen.SchedulePartyScreen.route)
            ) {
                if (viewModel.currentNavDestination != Screen.SchedulePartyScreen.route) {
                    viewModel.navigate(navController, Screen.SchedulePartyScreen.route)
                }
            }

            BottomMenuItem(
                ic = R.drawable.ic_bottom_bar_heart,
                selected = viewModel.isCurrentNavDestination.invoke(Screen.FavoritePartyScreen.route)
            ) {
                if (viewModel.currentNavDestination != Screen.FavoritePartyScreen.route) {
                    viewModel.navigate(navController, Screen.FavoritePartyScreen.route)
                }
            }

            BottomMenuItem(
                ic = R.drawable.ic_bottom_bar_profile,
                selected = viewModel.isCurrentNavDestination.invoke(Screen.ProfileScreen.route)
            ) {
                if (viewModel.currentNavDestination != Screen.ProfileScreen.route) {
                    viewModel.navigate(navController, Screen.ProfileScreen.route)
                }
            }
            
        }


    }

}

@Composable
private fun RowScope.BottomMenuItem(
    @DrawableRes ic: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    val animateSelectedColor by animateColorAsState(
        targetValue = if (selected) {
            animatedColorPrimary()
        } else {
            colorGray
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = ic),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = animateSelectedColor
            )
        }
    }

}