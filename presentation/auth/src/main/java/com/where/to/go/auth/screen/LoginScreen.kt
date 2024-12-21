package com.where.to.go.auth.screen

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.auth.R
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextFieldType
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.animateBrushPrimary
import com.where.to.go.component.colorContainerBg
import com.where.to.go.component.colorWhite
import com.where.to.go.component.primaryClip
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.RequestState
import com.where.to.go.main.MainActivity

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val context = LocalContext.current
    val loginState by viewModel.loginState.observeAsState(RequestState())

    when {
        loginState.isLoading -> {
            // Показать индикатор загрузки
        }
        loginState.error != null -> {
            Toast.makeText(context, loginState.error, Toast.LENGTH_LONG).show()
        }
        loginState.data != null -> {
            TokenManager.saveToken(loginState.data!!.token)
            TokenManager.saveEmail(viewModel.userEmail)
            Toast.makeText(context, "Вы вошли", Toast.LENGTH_LONG).show()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    BackHandler {
        //viewModel.clearUserData.invoke()
    }

    GlobalContainer(
        topBarStart = {
            SquareButton(icon = R.drawable.ic_back) {
                navController.popBackStack()
                viewModel.clearUserData.invoke()
            }

            AppText(text = stringResource(id = R.string.enter), weight = TextWeight.REGULAR, size = TextSize.TITLE_MEDIUM)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                AppTextField(
                    hint = stringResource(id = R.string.email),
                    value = viewModel.userEmail,
                    isError = viewModel.isValidEmail(),
                    type = TextFieldType.EMAIL,
                ) {
                    viewModel.enterUserName.invoke(it)
                    viewModel.checkSendable()
                }

                AppTextField(
                    hint = stringResource(id = R.string.password),
                    value = viewModel.userPassword,
                    type = TextFieldType.PASSWORD
                ) {
                    viewModel.enterUserPassword.invoke(it)
                    viewModel.checkSendable()
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppText(
                        text = "Ваша роль",
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE,
                        color = colorWhite
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(primaryClip())
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(colorContainerBg),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            UserRoleButton(value = stringResource(id = R.string.im_guest), status = viewModel.userRole == 0) {
                                viewModel.userRole = 0
                                viewModel.checkSendable()
                            }

                            UserRoleButton(value = stringResource(id = R.string.im_organizer), status = viewModel.userRole == 1) {
                                viewModel.userRole = 1
                                viewModel.checkSendable()
                            }
                        }
                    }
                }
            }

            PrimaryButton(value = "Продолжить", color = ButtonColor.COLORFUL) {
                if(viewModel.sendable){
                    viewModel.login(
                        AuthRequestModel(
                            role = viewModel.userRole,
                            email = viewModel.userEmail,
                            password = viewModel.userPassword
                        )
                    )
                } else{
                    Toast.makeText(context, "Не все поля правильно заполнены", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}

@Composable
private fun RowScope.UserRoleButton(
    value: String,
    status: Boolean,
    onClick: () -> Unit
) {
    val animateBgAlpha by animateFloatAsState(targetValue = if (status) 1f else 0f)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(primaryClip())
                .fillMaxWidth(.9f)
                .fillMaxHeight(.9f)
                .background(animateBrushPrimary(alpha = animateBgAlpha))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            AppText(
                text = value,
                weight = TextWeight.REGULAR,
                size = TextSize.BODY_LARGE
            )
        }
    }

}
