package com.where.to.go.main.utils

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.core.graphics.PathParser
import androidx.navigation.NavController
import com.gufo.custom.gufoshadow.shadow
import com.where.to.go.component.AppText
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.SquareButton
import com.where.to.go.component.values.animatedColorPrimary
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.colorGray
import com.where.to.go.component.primaryClip
import com.where.to.go.component.primaryFillWidth
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.offset
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.vms.NavigationViewModel
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import com.where.to.go.component.CustomSvgShape

@Composable
fun FragmentContainer(
    navController: NavController,
    navigationViewModel: NavigationViewModel,
    content: @Composable () -> Unit
) {
    val hamburgerMenuTimeMs = 400
    var hamburgerMenuState by remember { mutableStateOf(false) }
    val bottomNavState =  when (navigationViewModel.currentNavDestination) {
        Screen.ProfileScreen.route -> {
            BottomNavState(colorBg, true)
        }
        Screen.EditProfileScreen.route -> {
            BottomNavState(colorContainerBg, false)
        }
        else -> {
            BottomNavState(colorContainerBg, true)
        }
    }
    val padding = when (navigationViewModel.currentNavDestination) {
        Screen.ProfileScreen.route -> {
            0.dp
        }
        Screen.EditProfileScreen.route -> {
            0.dp
        }
        else -> {
            offset
        }
    }
    val animatedPadding by animateDpAsState(
        targetValue = padding,
        animationSpec = tween(durationMillis = 400), label = ""
    )
    GlobalContainer(
        topBarStart = {

            SquareButton(icon = R.drawable.ic_top_bar_menu) {
                hamburgerMenuState = !hamburgerMenuState
            }

            AppText(
                text = stringResource(
                    id = when (navigationViewModel.currentNavDestination) {
                        Screen.RecommendedScreen.route -> R.string.top_bar_recommend
                        Screen.SchedulePartyScreen.route -> R.string.top_bar_schedule
                        Screen.FavoritePartyScreen.route -> R.string.top_bar_favorite
                        Screen.ProfileScreen.route -> R.string.top_bar_profile
                        Screen.EditProfileScreen.route -> R.string.top_bar_edit_profile
                        Screen.SettingsScreen.route -> R.string.top_bar_settings
                        else -> R.string.error
                    }
                ),
                weight = TextWeight.REGULAR,
                size = TextSize.TITLE
            )
        },
        topBarEnd = {
            when (navigationViewModel.currentNavDestination) {
                Screen.ProfileScreen.route -> {
                    SquareButton(icon = R.drawable.ic_edit) {
                        navigationViewModel.navigate(Screen.EditProfileScreen.route)
                    }
                }
                Screen.EditProfileScreen.route -> {
                    SquareButton(icon = R.drawable.baseline_close_24) {
                        navigationViewModel.navigate(Screen.ProfileScreen.route)
                    }
                }
                else -> {
                    SquareButton(icon = R.drawable.ic_top_bar_settings) {
                        navigationViewModel.navigate(Screen.SettingsScreen.route)
                    }
                }
            }
        },
        horizontalOffset = animatedPadding
    ) {
        content()
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if(bottomNavState.color != colorBg){
            Box(
                modifier = Modifier
                    .fillMaxWidth().height(150.dp).align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.23f)
                            )
                        )
                    )
            )
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            BottomMenu(viewModel = navigationViewModel, state = bottomNavState)
        }

        HamburgerMenu(
            enable = hamburgerMenuState,
            timeAnim = hamburgerMenuTimeMs,
            navController = navController,
            viewModel = navigationViewModel,
        ) {
            hamburgerMenuState = it
        }
    }

}

data class BottomNavState(
    val color: Color,
    val isOpen: Boolean
)

@Composable
private fun BottomMenu(
    state: BottomNavState,
    viewModel: NavigationViewModel,
    modifier: Modifier = Modifier
) {
    val svgPathData = "M0 53.9438C0 29.5073 19.5018 9.53677 43.9314 8.9565L421 0L798.069 8.9565C822.498 9.53677 842 29.5073 842 53.9438V134.056C842 158.493 822.498 178.463 798.069 179.044L421 188L43.9314 179.044C19.5018 178.463 0 158.493 0 134.056V53.9438Z"

    val customShape = CustomSvgShape(svgPathData)
    AnimatedVisibility(
        visible = state.isOpen,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
    ) {
        Box(
            modifier = modifier
                /*.shadow(
                    color = animatedColorPrimary(),
                    borderRadius = 16.dp,
                    blurRadius = 5.dp
                )*/
                .fillMaxWidth()
                .height(90.dp)
                .padding(horizontal = 20.dp)
                .background(state.color, shape = customShape),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomMenuItem(
                    ic = R.drawable.ic_party,
                    selected = viewModel.isCurrentNavDestination.invoke(Screen.RecommendedScreen.route)
                ) {
                    if (viewModel.currentNavDestination != Screen.RecommendedScreen.route) {
                        viewModel.navigate(Screen.RecommendedScreen.route)
                    }
                }

                BottomMenuItem(
                    ic = R.drawable.ic_calendar,
                    selected = viewModel.isCurrentNavDestination.invoke(Screen.SchedulePartyScreen.route)
                ) {
                    if (viewModel.currentNavDestination != Screen.SchedulePartyScreen.route) {
                        viewModel.navigate(Screen.SchedulePartyScreen.route)
                    }
                }

                BottomMenuItem(
                    ic = R.drawable.ic_heart,
                    selected = viewModel.isCurrentNavDestination.invoke(Screen.FavoritePartyScreen.route)
                ) {
                    if (viewModel.currentNavDestination != Screen.FavoritePartyScreen.route) {
                        viewModel.navigate(Screen.FavoritePartyScreen.route)
                    }
                }

                BottomMenuItem(
                    ic = R.drawable.ic_profile,
                    selected = viewModel.isCurrentNavDestination.invoke(Screen.ProfileScreen.route)
                ) {
                    if (viewModel.currentNavDestination != Screen.ProfileScreen.route) {
                        viewModel.navigate(Screen.ProfileScreen.route)
                    }
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
        }, label = ""
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
    viewModel: NavigationViewModel,
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
                                    viewModel.navigate(Screen.ProfileScreen.route)
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
                                TokenManager.clearToken()
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