package com.where.to.go.feature_auth.screen

import android.util.Log
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
import com.where.to.go.core.cases.auth.AuthUseCase
import com.where.to.go.data.plugins.TokenManager
import com.where.to.go.domain.ConfirmCodeModel
import com.where.to.go.domain.model.RequestState

@Composable
fun VerificationScreen(
    authUseCase: AuthUseCase,
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val context = LocalContext.current
    val confirmCodeState by viewModel.confirmCodeState.observeAsState(RequestState())

    when {
        confirmCodeState.isLoading -> {
            // Показать индикатор загрузки
        }
        confirmCodeState.error != null -> {
            Log.e("AUTOLOGIN", confirmCodeState.error.toString())
        }
        confirmCodeState.data != null -> {
            com.where.to.go.data.plugins.TokenManager.saveToken(confirmCodeState.data!!.token)
            Toast.makeText(context, "Код верный", Toast.LENGTH_LONG).show()
            navController.navigate(Screen.ResetPasswordScreen.route)
        }
    }
    BackHandler {
        //viewModel.clearUserData.invoke()
    }

    GlobalContainer(
        topBarStart = {
            SquareButton(icon = R.drawable.ic_back) {
                navController.navigate(Screen.StartScreen.route)
                //viewModel.clearUserData.invoke()
            }

            AppText(text = stringResource(id = R.string.verification), weight = TextWeight.REGULAR, size = TextSize.TITLE)
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
                    hint = stringResource(id = R.string.restore_code),
                    value = viewModel.restoreCode,
                    type = TextFieldType.TEXT,
                ) {
                    viewModel.enterRestoreCode.invoke(it)
                    viewModel.checkSendable()
                }


            }

            PrimaryButton(value = "Подтвердить", color = ButtonColor.COLORFUL) {
                viewModel.confirmCode(ConfirmCodeModel(viewModel.restoreCode, viewModel.userEmail))
            }
        }

    }

}
