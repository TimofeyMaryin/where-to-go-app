package com.where.to.go.auth.screen

import android.content.Intent
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.auth.R
import com.where.to.go.auth.navigation.Screen
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
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.ConfirmCodeModel
import com.where.to.go.internet.models.RequestState
import com.where.to.go.main.MainActivity

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
            TokenManager.saveToken(confirmCodeState.data!!.token)
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

            AppText(text = stringResource(id = R.string.verification), weight = TextWeight.REGULAR, size = TextSize.TITLE_MEDIUM)
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
