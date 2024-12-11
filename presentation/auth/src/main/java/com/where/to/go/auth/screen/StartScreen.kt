package com.where.to.go.auth.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.auth.R
import com.where.to.go.auth.navigation.Screen
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.component.AnimateText
import com.where.to.go.component.AppText
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import kotlinx.coroutines.delay
import kotlin.system.exitProcess

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            Image(
                painter = painterResource(id = R.drawable.splash_image),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
        }

        Box(modifier = Modifier.fillMaxSize(.9f), contentAlignment = Alignment.TopEnd) {
            SquareButton(icon = R.drawable.ic_close) {
                exitProcess(0)
            }
        }

        Box(modifier = Modifier.fillMaxSize(.9f), contentAlignment = Alignment.BottomCenter) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimateText(value = stringResource(id = R.string.where_to_go), weight = TextWeight.BOLD, size = TextSize.HEADLINE)

                AppText(text = stringResource(id = R.string.start_screen_label), weight = TextWeight.REGULAR, size = TextSize.TITLE_MEDIUM)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {

                    PrimaryButton(value = stringResource(id = R.string.enter), color = ButtonColor.COLORFUL) {
                        navController.navigate(Screen.LoginScreen.route)
                    }

                    PrimaryButton(value = stringResource(id = R.string.signup), color = ButtonColor.BORDER) {
                        navController.navigate(Screen.AuthScreen.route)
                    }

                }
            }
        }

    }


}