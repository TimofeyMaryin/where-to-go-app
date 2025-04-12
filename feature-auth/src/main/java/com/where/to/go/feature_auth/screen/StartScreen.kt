package com.where.to.go.feature_auth.screen

import android.content.Intent
import android.util.Log
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.feature_auth.R
import com.where.to.go.feature_auth.navigation.Screen
import com.where.to.go.feature_auth.vms.AuthViewModel
import com.where.to.go.common_ui.AnimateText
import com.where.to.go.common_ui.AppText
import com.where.to.go.common_ui.ButtonColor
import com.where.to.go.common_ui.PrimaryButton
import com.where.to.go.common_ui.SquareButton
import com.where.to.go.common_ui.values.TextSize
import com.where.to.go.common_ui.values.TextWeight
import com.where.to.go.common_ui.values.animatedColorPrimary
import com.where.to.go.domain.model.RequestState
import com.where.to.go.data.plugins.TokenManager
import com.where.to.go.main.MainActivity
import kotlin.system.exitProcess

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {

    val context = LocalContext.current
    val autoLoginState by viewModel.autoLoginState.observeAsState(RequestState())

    when {
        autoLoginState.isLoading -> {
            // Показать индикатор загрузки
        }
        autoLoginState.error != null -> {
            Log.e("AUTOLOGIN", autoLoginState.error.toString())
        }
        autoLoginState.data != null -> {
            com.where.to.go.data.plugins.TokenManager.clearToken()
            com.where.to.go.data.plugins.TokenManager.saveToken(autoLoginState.data!!.token)
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    val transition = rememberInfiniteTransition()
    val progressIndicatorState = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            tween(400)
        ), label = ""
    )
    LaunchedEffect(key1 = Unit) {
        if(!com.where.to.go.data.plugins.TokenManager.getToken().isNullOrEmpty() && !autoLoginState.isLoading) viewModel.autoLogin()
    }
    Box(modifier = Modifier
        .padding(bottom = 18.dp)
        .fillMaxSize(), contentAlignment = Alignment.Center) {

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

        Box(modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxSize(.9f), contentAlignment = Alignment.BottomCenter) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimateText(value = stringResource(id = R.string.where_to_go), weight = TextWeight.BOLD, size = TextSize.HEADLINE)

                AppText(text = stringResource(id = R.string.start_screen_label), weight = TextWeight.REGULAR, size = TextSize.TITLE)

                if (!autoLoginState.isLoading) {
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
                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            progress = progressIndicatorState.value,
                            modifier = Modifier.size(35.dp),
                            color = animatedColorPrimary()
                        )

                        AppText(
                            text = stringResource(id = R.string.load_start_screen),
                            weight = TextWeight.REGULAR,
                            size = TextSize.BODY_LARGE
                        )
                    }
                }
            }
        }
    }


}