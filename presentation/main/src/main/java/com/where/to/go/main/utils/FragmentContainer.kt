package com.where.to.go.main.utils

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gufo.custom.gufoshadow.shadow
import com.where.to.go.component.AppText
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.animatedColorPrimary
import com.where.to.go.component.brushHamburgerBg
import com.where.to.go.component.colorBg
import com.where.to.go.component.colorContainerBg
import com.where.to.go.component.colorGray
import com.where.to.go.component.primaryClip
import com.where.to.go.component.primaryFillWidth
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.vms.RecommendedViewModel

@Composable
fun FragmentContainer(
    navController: NavController,
    viewModel: RecommendedViewModel,
    content: @Composable () -> Unit
) {


    val hamburgerMenuTimeMs = 400
    var hamburgerMenuState by remember { mutableStateOf(false) }


    GlobalContainer(
        topBarStart = {

            SquareButton(icon = R.drawable.ic_top_bar_menu) {
                hamburgerMenuState = !hamburgerMenuState
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
        },
    ) {

        content()

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Box(
                modifier = Modifier.fillMaxSize(primaryFillWidth),
                contentAlignment = Alignment.BottomCenter
            ) {
                BottomMenu(navController = navController, viewModel = viewModel,)
            }

            HamburgerMenu(
                enable = hamburgerMenuState,
                timeAnim = hamburgerMenuTimeMs,
                navController = navController,
                viewModel = viewModel,
            ) {
                hamburgerMenuState = it
            }
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
            .shadow(
                color = animatedColorPrimary(),
                borderRadius = 16.dp,
                blurRadius = 5.dp
            )
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


@Composable
fun HamburgerMenu(
    enable: Boolean,
    viewModel: RecommendedViewModel,
    navController: NavController,
    timeAnim: Int,
    isFull: Boolean = false,
    onChangeState: (Boolean) -> Unit
) {
    val context = LocalContext.current

    AnimatedVisibility(
        visible = enable,
        enter = slideInHorizontally(tween(timeAnim)),
        exit = slideOutHorizontally(tween(timeAnim)) { -it }
    ) {
        var offsetX by remember { mutableStateOf(0f) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { }
                },
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.fillMaxSize(if (isFull) .95f else 1f), contentAlignment = Alignment.CenterStart) {

                Box(
                    modifier = Modifier
                        .shadow(
                            color = animatedColorPrimary(),
                            borderRadius = 16.dp,
                            blurRadius = 5.dp
                        )
                        .clip(primaryClip())
                        .fillMaxHeight()
                        .fillMaxWidth(primaryFillWidth)
                        .offset(x = offsetX.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->

                                offsetX += dragAmount.x

                                if (dragAmount.x < -4) {
                                    onChangeState(false)
                                }

                                Log.e(
                                    "TAG",
                                    "FragmentContainer offset: $offsetX | dragAmount.x: ${dragAmount.x}",
                                )
                            }
                        }
                        .background(colorContainerBg),
                    contentAlignment = Alignment.Center,
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(primaryFillWidth)
                            .fillMaxHeight(.8f),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.avatar_test),
                                contentDescription = null,
                                modifier = Modifier
                                    .shadow(
                                        color = animatedColorPrimary(),
                                        borderRadius = 16.dp,
                                        blurRadius = 5.dp,
                                        offsetX = offsetX.dp
                                    )
                                    .clip(primaryClip())
                                    .size(120.dp)
                                    .aspectRatio(1f),
                                contentScale = ContentScale.Crop
                            )

                            AppText(
                                text = "UserModel.name",
                                weight = TextWeight.BOLD,
                                size = TextSize.TITLE_LARGE
                            )

                            Divider()

                            HamburgerMenuButton(img = null, text = "Профиль") {
                                if (viewModel.currentNavDestination != Screen.ProfileScreen.route) {
                                    viewModel.navigate(navController, Screen.ProfileScreen.route)
                                }
                                onChangeState(false)
                            }

                            HamburgerMenuButton(img = null, text = "Оплата") {

                            }

                            HamburgerMenuButton(
                                img = R.drawable.hamburger_menu_button_img_1,
                                text = "Что-то еще"
                            ) {

                            }

                            HamburgerMenuButton(img = null, text = "Logout") {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("myapp://moduleAuth/auth"))
                                context.startActivity(intent)

                            }

                        }
                    }

                }
            }

        }
    }
}

@Composable
private fun HamburgerMenuButton(
    @DrawableRes img: Int?,
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(primaryClip())
            .fillMaxWidth()
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (img != null) {
                Image(painter = painterResource(id = img), contentDescription = null, modifier = Modifier.size(25.dp), contentScale = ContentScale.Crop)
            } else {
                Spacer(modifier = Modifier.size(25.dp))
            }

            AppText(text = text, weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE)
        }
    }
    
}