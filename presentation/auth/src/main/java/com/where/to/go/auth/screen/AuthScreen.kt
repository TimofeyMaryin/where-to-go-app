package com.where.to.go.auth.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.where.to.go.auth.R
import com.where.to.go.auth.navigation.Screen
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.component.AddPersonalData
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.CheckBoxParent
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.PersonalType
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextFieldType
import com.where.to.go.component.values.blue
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.pink
import com.where.to.go.component.primaryClip
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.internet.models.RequestState

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val context = LocalContext.current
    var showAlertForFillPersonalData by remember { mutableStateOf<PersonalType?>(null) }
    val signupState by viewModel.signupState.observeAsState(RequestState())

    when {
        signupState.isLoading -> {
            // Показать индикатор загрузки
        }
        signupState.error != null -> {
            Toast.makeText(context, signupState.error, Toast.LENGTH_LONG).show()
        }
        signupState.data != null -> {
            navController.navigate(Screen.LoginScreen.route)
            Toast.makeText(context, "Аккаунт создан, авторизуйтесь", Toast.LENGTH_LONG).show()
        }
    }

    BackHandler {
        navController.popBackStack()
        //viewModel.clearUserData.invoke()
    }

    GlobalContainer(
        topBarStart = {
            SquareButton(icon = R.drawable.ic_back) {
                navController.popBackStack()
                viewModel.clearUserData.invoke()
            }
            
            AppText(
                text = stringResource(id = R.string.auth),
                weight = TextWeight.REGULAR,
                size = TextSize.TITLE_MEDIUM
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                CheckBoxParent(
                    status = viewModel.userRole == 0,
                    value = stringResource(id = R.string.im_guest)
                ) {
                    viewModel.enterUserAccType.invoke(0)
                }
            }
            item {
                CheckBoxParent(
                    status = viewModel.userRole == 1,
                    value = stringResource(id = R.string.im_organizer)
                ) {
                    viewModel.enterUserAccType.invoke(1)
                }
            }
            item {
                AppTextField(
                    hint = stringResource(id = R.string.email),
                    value = viewModel.userEmail,
                    type = TextFieldType.EMAIL,
                    isError = viewModel.isValidEmail()
                ) {
                    viewModel.enterUserName.invoke(it)
                    viewModel.checkSendable()
                }
            }
            item {
                AppTextField(
                    hint = stringResource(id = R.string.password),
                    value = viewModel.userPassword,
                    type = TextFieldType.PASSWORD
                ) {
                    viewModel.enterUserPassword.invoke(it)
                    viewModel.checkSendable()
                }

            }
            item {
                AddPersonalData(img = com.where.to.go.component.R.drawable.telegram, value = viewModel.userTelegram) {
                    showAlertForFillPersonalData = PersonalType.TG
                }
            }
            item {
                AddPersonalData(img = com.where.to.go.component.R.drawable.vk, value = viewModel.userVK) {
                    showAlertForFillPersonalData = PersonalType.VK
                }
            }

            item {
                AddPersonalData(img = com.where.to.go.component.R.drawable.phone, value = viewModel.userPhone) {
                    showAlertForFillPersonalData = PersonalType.PHONE
                }
            }

            item {
                PrimaryButton(
                    value = stringResource(id = R.string.enter),
                    color = ButtonColor.COLORFUL
                ) {
                    if(viewModel.sendable){
                        viewModel.signup(AuthRequestModel(viewModel.userRole, viewModel.userEmail, viewModel.userPassword))
                    }
                    else{
                        Toast.makeText(context, "Не все поля правильно заполнены", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }
    }

    if (showAlertForFillPersonalData != null) {
        var personalData by remember {
            mutableStateOf(
                when (showAlertForFillPersonalData) {
                    PersonalType.TG -> viewModel.userTelegram
                    PersonalType.VK -> viewModel.userVK
                    PersonalType.PHONE -> viewModel.userPhone
                    null -> ""
                }
            )
        }
        val transition = rememberInfiniteTransition()
        val animateIconColor by transition.animateColor(
            initialValue = pink,
            targetValue = blue,
            animationSpec = infiniteRepeatable(
                tween(500),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )

        AlertDialog(
            onDismissRequest = { showAlertForFillPersonalData = null },
            shape = primaryClip(),
            containerColor = colorBg,
            confirmButton = {
                PrimaryButton(value = "Отмена", color = ButtonColor.BORDER) {
                    personalData = ""
                    showAlertForFillPersonalData = null
                }
            },
            dismissButton = {
                PrimaryButton(value = "Сохранить", color = ButtonColor.COLORFUL) {
                    when (showAlertForFillPersonalData) {
                        PersonalType.TG -> viewModel.userTelegram = personalData
                        PersonalType.VK -> viewModel.userVK = personalData
                        PersonalType.PHONE -> viewModel.userPhone = personalData
                        null -> throw IllegalArgumentException("Долбаеб?")
                    }
                    showAlertForFillPersonalData = null
                }
            },
            title = {
                    AppText(
                        text = "Добавление персональных данных",
                        weight = TextWeight.BOLD,
                        size = TextSize.TITLE_MEDIUM,
                        textAlign = TextAlign.Center
                    )
            },
            icon = {
                   Icon(
                       imageVector = Icons.Default.Info,
                       contentDescription = null,
                       tint = animateIconColor
                   )
            },
            text = {
                AppTextField(
                    hint = when (showAlertForFillPersonalData) {
                        PersonalType.TG -> "Введите свой ID"
                        PersonalType.VK -> "Введите свой ID"
                        PersonalType.PHONE -> "Введите номер телефона"
                        null -> "Error"
                    },
                    value = personalData,
                    type = when (showAlertForFillPersonalData) {
                        PersonalType.TG -> TextFieldType.TEXT
                        PersonalType.VK -> TextFieldType.TEXT
                        PersonalType.PHONE -> TextFieldType.PHONE
                        null -> throw IllegalArgumentException("Еблан?")
                    }
                ) {
                    if (showAlertForFillPersonalData == PersonalType.PHONE) {
                        if (it.isDigitsOnly() && it.length <= 11) {
                            personalData = it
                        }
                    } else {
                        personalData = it
                    }
                }
            }
        )
    }
}

