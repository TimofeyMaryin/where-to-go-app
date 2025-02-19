package com.where.to.go.main.fragment

import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.Container
import com.where.to.go.component.PersonalType
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.ProfileBackground
import com.where.to.go.component.TextFieldType
import com.where.to.go.component.values.animateIconColor
import com.where.to.go.component.values.animatedColorPrimary
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.colorGray
import com.where.to.go.component.primaryClip
import com.where.to.go.component.primaryFillWidth
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.offset
import com.where.to.go.component.values.shortOffset
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.vms.NavigationViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.UserDataChangedCallback

@Composable
fun ProfileFragment(
    viewModel: ProfileViewModel,
    navigationViewModel: NavigationViewModel,
    userUseCase: UserUseCase
) {
    val context = LocalContext.current

    var openDialogFillPersonalData by remember { mutableStateOf<PersonalType?>(null) }
    var userPersonalData by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var totalWeightForBackgroundElement by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .collect { layoutInfo ->
                totalWeightForBackgroundElement = (
                        (layoutInfo.visibleItemsInfo.sumOf { it.size } +
                                lazyListState.firstVisibleItemScrollOffset.toFloat()) / 5000f)
            }

    }

    val avatarSize = 120.dp
    val paddingTop = 80.dp

    ProfileBackground(modifier = Modifier.padding(top = paddingTop))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingTop - avatarSize / 2, start = offset, end = offset)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = viewModel.loginUser?.avatar?.toIntOrNull() ?: R.drawable.avatar_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .clip(primaryClip())
                    .border(
                        width = 1.dp,
                        color = animatedColorPrimary(),
                        shape = primaryClip()
                    )
                    .size(avatarSize)
                    .clickable {  },
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(avatarSize) ){
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.TopStart)
                        .padding(
                            start = shortOffset,
                            top = shortOffset,
                            bottom = shortOffset
                        ),
                    verticalArrangement = Arrangement.Top,
                ) {
                    AppText(
                        text = viewModel.loginUser?.name ?: "User${viewModel.loginUser?.id}",
                        weight = TextWeight.BOLD,
                        size = TextSize.TITLE_MEDIUM
                    )
                    AppText(
                        text = "email: ${TokenManager.getEmail()}",
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE
                    )

                    Spacer(Modifier.height(30.dp))
                }
                Row(
                    modifier = Modifier
                        .padding(top = paddingTop, start = shortOffset)
                        .align(Alignment.TopStart),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.geo_tag),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    AppText(
                        text = viewModel.loginUser?.region ?: "---",
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE
                    )
                }
            }

        }
        Spacer(Modifier.height(1.dp))
        AppText(
            text =  stringResource(id = R.string.about),
            weight = TextWeight.BOLD,
            size = TextSize.TITLE_MEDIUM
        )
        /*ProfilePersonalData(
            theme = stringResource(id = R.string.about),
            value = viewModel.loginUser?.status ?: "---"
        )*/
            viewModel.loginUser?.description?.let { AppText(text = it) }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        /*Row {
     SocialInfoBanner(
         img = com.where.to.go.component.R.drawable.telegram,
         value = viewModel.loginUser?.tg
     ) {
         openDialogFillPersonalData = PersonalType.TG
     }

     SocialInfoBanner(
         img = com.where.to.go.component.R.drawable.vk,
         value = viewModel.loginUser?.vk
     ) {
         openDialogFillPersonalData = PersonalType.VK
     }

     SocialInfoBanner(
         img = com.where.to.go.component.R.drawable.phone,
         value = viewModel.loginUser?.phone
     ) {
         openDialogFillPersonalData = PersonalType.PHONE
     }
 }*/

    }

    if (openDialogFillPersonalData != null) {
        AlertDialog(
            onDismissRequest = {
                openDialogFillPersonalData = null
                userPersonalData = ""
            },
            shape = primaryClip(),
            containerColor = colorBg,
            confirmButton = {
                PrimaryButton(value = "Отмена", color = ButtonColor.BORDER) {
                    openDialogFillPersonalData = null
                    userPersonalData = ""
                }
            },
            dismissButton = {
                PrimaryButton(value = "Сохранить", color = ButtonColor.COLORFUL) {

                }
                try{
                    viewModel.updateUserData(
                        newUser = viewModel.
                        loginUser?.
                        copy(
                            tg = userPersonalData
                        ) ?: throw IllegalArgumentException("Compose cannot update user"),
                        callback = object : UserDataChangedCallback {
                            override fun onError(msg: String) {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                                Log.e("TAG", "onError updateUserData compose: $msg", )
                                openDialogFillPersonalData = null
                                userPersonalData = ""
                            }

                            override fun onSuccess(msg: String) {
                                Toast.makeText(context, "Success $msg", Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
                }catch(e: Exception){
                    Log.e("TESTAG", e.toString())
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
                    tint = animateIconColor()
                )
            },
            text = {
                AppTextField(
                    hint = when (openDialogFillPersonalData) {
                        PersonalType.TG -> "Введите свой ID"
                        PersonalType.VK -> "Введите свой ID"
                        PersonalType.PHONE -> "Введите номер телефона"
                        null -> "Error"
                    },
                    value = userPersonalData,
                    type = when (openDialogFillPersonalData) {
                        PersonalType.TG -> TextFieldType.TEXT
                        PersonalType.VK -> TextFieldType.TEXT
                        PersonalType.PHONE -> TextFieldType.PHONE
                        null -> TextFieldType.TEXT
                    }
                ) {
                    if (openDialogFillPersonalData == PersonalType.PHONE) {
                        if (it.isDigitsOnly() && it.length <= 11) {
                            userPersonalData = it
                        }
                    } else {
                        userPersonalData = it
                    }
                }
            }
        )

    }
}


@Composable
private fun ProfilePersonalData(
    theme: String,
    value: String,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AppText(text = theme, weight = TextWeight.MEDIUM, size = TextSize.BODY_LARGE)

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .clip(primaryClip())
                .fillMaxWidth()
                .background(colorGray),
            contentAlignment = Alignment.Center
        ) {
            AppText(
                text = value,
                weight = TextWeight.REGULAR,
                size = TextSize.BODY_LARGE,
                modifier = Modifier
                    .fillMaxWidth(primaryFillWidth)
                    .padding(vertical = 16.dp),
                color = colorBg,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
private fun RowScope.SocialInfoBanner(
    @DrawableRes img: Int,
    value: String?,
    onClick: () -> Unit
) {

    Container(weight = 1f) {
        Box(
            modifier = Modifier
                .clip(primaryClip())
                .background(colorGray)
                .size(120.dp)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(primaryFillWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "Social img",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Crop
                )

                AppText(
                    text = value ?: "Добавить",
                    weight = TextWeight.REGULAR,
                    size = TextSize.BODY_LARGE,
                    textDecoration = if (value == null) TextDecoration.Underline else null
                )
            }
        }

    }

}
