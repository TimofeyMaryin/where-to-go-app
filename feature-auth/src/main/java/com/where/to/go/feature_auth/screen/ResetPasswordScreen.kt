package com.where.to.go.feature_auth.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.feature_auth.R
import com.where.to.go.feature_auth.navigation.Screen
import com.where.to.go.feature_auth.vms.AuthViewModel
import com.where.to.go.common_ui.AppText
import com.where.to.go.common_ui.AppTextField
import com.where.to.go.common_ui.ButtonColor
import com.where.to.go.common_ui.GlobalContainer
import com.where.to.go.common_ui.PrimaryButton
import com.where.to.go.common_ui.SquareButton
import com.where.to.go.common_ui.values.TextFieldType
import com.where.to.go.common_ui.values.TextSize
import com.where.to.go.common_ui.values.TextWeight
import com.where.to.go.domain.model.RequestState
import com.where.to.go.domain.ResetPasswordModel

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val context = LocalContext.current

    val resetPasswordState by viewModel.resetPasswordState.observeAsState(RequestState())

    when {
        resetPasswordState.isLoading -> {
            // Показать индикатор загрузки
        }
        resetPasswordState.error != null -> {
            Toast.makeText(context, "Ошибка сервера ${resetPasswordState.error}", Toast.LENGTH_LONG).show()
        }
        resetPasswordState.data != null -> {
            Toast.makeText(context, "Пароль изменен", Toast.LENGTH_LONG).show()
            navController.navigate(Screen.LoginScreen.route)
        }
    }

    BackHandler {
    }

    GlobalContainer(
        topBarStart = {
            SquareButton(icon = R.drawable.ic_back) {
                navController.navigate(Screen.StartScreen.route)
            }

            AppText(text = stringResource(id = R.string.reset_password), weight = TextWeight.REGULAR, size = TextSize.TITLE)
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
                    hint = stringResource(id = R.string.password),
                    value = viewModel.userPassword,
                    type = TextFieldType.PASSWORD
                ) {
                    viewModel.enterUserPassword.invoke(it)
                    viewModel.checkSendable()
                }

            }


            PrimaryButton(value = "Сменить пароль", color = ButtonColor.COLORFUL) {
                viewModel.resetPassword(ResetPasswordModel(viewModel.userEmail, viewModel.userPassword))
            }
        }

    }

}