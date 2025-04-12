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
import com.where.to.go.domain.model.RequestState
import com.where.to.go.domain.RestorePasswordModel

@Composable
fun RestoreScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val context = LocalContext.current
    val restorePasswordState by viewModel.restorePasswordState.observeAsState(RequestState())

    when {
        restorePasswordState.isLoading -> {
            // Показать индикатор загрузки
        }
        restorePasswordState.error != null -> {
            Log.e("AUTOLOGIN", restorePasswordState.error.toString())
        }
        restorePasswordState.data != null -> {
            navController.navigate(Screen.VerificationScreen.route)
            Toast.makeText(context, "Код отправлен", Toast.LENGTH_LONG).show()
        }
    }
    BackHandler {
    }

    GlobalContainer(
        topBarStart = {
            SquareButton(icon = R.drawable.ic_back) {
                navController.popBackStack()
            }

            AppText(text = stringResource(id = R.string.restore), weight = TextWeight.REGULAR, size = TextSize.TITLE)
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


            }

            PrimaryButton(value = stringResource(id = R.string.send_code), color = ButtonColor.COLORFUL) {
                viewModel.restorePassword(RestorePasswordModel(viewModel.userEmail))
            }

        }

    }

}