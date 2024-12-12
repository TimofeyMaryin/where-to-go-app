package com.where.to.go.auth.screen

import android.content.Context
import android.content.Intent
import android.media.session.MediaSession.Token
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.where.to.go.auth.AuthActivity
import com.where.to.go.auth.R
import com.where.to.go.auth.plugins.TokenManager
import com.where.to.go.auth.vms.AuthViewModel
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.CheckBoxParent
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.SmallCheckBox
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextFieldType
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.blue
import com.where.to.go.component.colorBg
import com.where.to.go.component.pink
import com.where.to.go.component.primaryClip
import com.where.to.go.internet.cases.AuthUseCase
import com.where.to.go.internet.models.AuthRequestModel
import com.where.to.go.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    authUseCase: AuthUseCase,
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val context = LocalContext.current

    var showAlertForFillPersonalData by remember { mutableStateOf<PersonalType?>(null) }
    val scope = rememberCoroutineScope()

    BackHandler {
        navController.popBackStack()
        viewModel.clearUserData.invoke()
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                }
            }
            item {
                AppTextField(
                    hint = stringResource(id = R.string.password),
                    value = viewModel.userPassword,
                    type = TextFieldType.PASSWORD
                ) {
                    viewModel.enterUserPassword.invoke(it)
                }

            }
            item {
                AddPersonalData(img = R.drawable.telegram, value = viewModel.userTelegram) {
                    showAlertForFillPersonalData = PersonalType.TG
                }
            }
            item {
                AddPersonalData(img = R.drawable.vk, value = viewModel.userVK) {
                    showAlertForFillPersonalData = PersonalType.VK
                }
            }

            item {
                AddPersonalData(img = R.drawable.phone, value = viewModel.userPhone) {
                    showAlertForFillPersonalData = PersonalType.PHONE
                }
            }

            item {
                PrimaryButton(
                    value = stringResource(id = R.string.enter),
                    color = ButtonColor.COLORFUL
                ) {
                    handleSignup(
                        context = context,
                        authUseCase = authUseCase,
                        coroutineScope = scope,
                        email = viewModel.userEmail,
                        password = viewModel.userPassword,
                        onLoading = {
                            Log.e("TAG - Auth", "AuthScreen load: $it", )

                        },
                        onResult = { msg: String ->
                            Log.e("TAG", "AuthScreen: $msg", )
                        },
                        phone = viewModel.userPhone ?: "",
                        role = viewModel.userRole
                    )

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

enum class PersonalType { TG, VK, PHONE }
@Composable
private fun AddPersonalData(
    @DrawableRes img: Int,
    value: String,
    onAdd: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    contentScale = ContentScale.Crop
                )

                TextButton(onClick = { onAdd() }) {
                    AppText(
                        text = value.ifEmpty { stringResource(id = R.string.add) },
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE
                    )
                }

            }
            SmallCheckBox(status = value.isNotEmpty())
        }
    }


}


fun handleSignup(
    context: Context,
    authUseCase: AuthUseCase,
    email: String,
    phone: String,
    password: String,
    role: Int,
    coroutineScope: CoroutineScope,
    onLoading: (Boolean) -> Unit,
    onResult: (String) -> Unit
) {
    coroutineScope.launch {
        onLoading(true)

        try {
            val response = authUseCase.signup(AuthRequestModel(email = email, phone = phone, password = password, role = role))

            if (response.isSuccessful) {
                Log.e("TAG - handle-signup", "handleSignup: ${response.body()}", )
                onResult("Success handleSignup: ${response.body()}")
                handleLogin(
                    context = context,
                    authUseCase = authUseCase,
                    email = email,
                    role = role,
                    password = password,
                    coroutineScope = coroutineScope,
                    onLoading = onLoading,
                    onResult = onResult,
                )

            } else {
                onResult("Ошибка handleSignup_1: ${response.message()}")
            }
        } catch (e: Exception) {
            onResult("Ошибка handleSignup_2: ${e.message}")
        } finally {
            onLoading(false)
        }
    }
}


fun handleLogin(
    context: Context,
    authUseCase: AuthUseCase,
    email: String,
    role: Int,
    password: String,
    coroutineScope: CoroutineScope,
    onLoading: (Boolean) -> Unit,
    onResult: (String) -> Unit

) {
    coroutineScope.launch {
        onLoading(true)
        try {
            val response = authUseCase.login(AuthRequestModel(email = email, phone = "", password = password, role = role))
            if (response.isSuccessful) {
                val token = response.body()?.token ?: "Токен отсутствует"
                onResult("Успешный вход: $token")

                TokenManager.apply {
                    init(context)
                    saveToken(token)
                }
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            } else {
                onResult("Ошибка: ${response.message()}")
            }
        } catch (e: Exception) {
            onResult("Ошибка: ${e.message}")
        } finally {
            onLoading(false)
        }
    }
}


