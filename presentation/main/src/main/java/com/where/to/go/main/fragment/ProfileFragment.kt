package com.where.to.go.main.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.where.to.go.component.AddPersonalData
import com.where.to.go.component.AppText
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.Container
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.animatedColorPrimary
import com.where.to.go.component.colorContainerBg
import com.where.to.go.component.primaryClip
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.dao.UserService
import com.where.to.go.internet.models.AuthResponseModel
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.internet.servers.UserServer
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.utils.ImagePicker
import com.where.to.go.main.vms.ProfileViewModel
import java.security.AccessController.getContext

@Composable
fun ProfileFragment(
    navController: NavController,
    viewModel: ProfileViewModel,
    userUseCase: UserUseCase
) {
    val context = LocalContext.current
    val imagePicker = remember { ImagePicker() }
    val getImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            uri?.let {
                context.contentResolver.openInputStream(it)?.let { inputStream ->
                    imagePicker.loadImage(it, inputStream)
                    val encodedUri = Uri.encode(uri.toString())
                    navController.navigate("${Screen.ImageEditorScreen.route}/${encodedUri}")
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f))

        Box(modifier = Modifier
            .clip(primaryClip())
            .fillMaxWidth()
            .weight(7f)
            .fillMaxHeight()
            .background(
                colorContainerBg
            ))
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier.fillMaxWidth(.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Container(weight = 1f) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Container(weight = .7f) {

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
                                .size(100.dp)
                                .clickable(onClick = {
                                    imagePicker.pickImage(context as Activity, getImageLauncher)
                                }),
                            contentScale = ContentScale.Crop

                        )

                    }

                    Container(weight = 1.4f) {

                        Column(
                            modifier = Modifier.fillMaxSize(.9f),
                            verticalArrangement = Arrangement.SpaceAround
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

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.geo_tag),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    contentScale = ContentScale.FillWidth
                                )

                                AppText(
                                    text = "Нижний Новгород",
                                    weight = TextWeight.REGULAR,
                                    size = TextSize.BODY_LARGE
                                )
                            }
                        }

                    }
                }
            }


            Container(weight = 1.8f) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AppText(text = stringResource(id = R.string.about), weight = TextWeight.MEDIUM, size = TextSize.BODY_LARGE)

                    AppText(
                        text = viewModel.loginUser?.description ?: "",
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE,
                        lineHeight = 18
                    )
                }
            }

            Container(weight = 1.8f) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    AddPersonalData(img = com.where.to.go.component.R.drawable.telegram, value = viewModel.loginUser?.tg ?: "") {

                    }

                    AddPersonalData(img = com.where.to.go.component.R.drawable.vk, value = viewModel.loginUser?.vk ?: "") {

                    }

                    AddPersonalData(img = com.where.to.go.component.R.drawable.phone, value = viewModel.loginUser?.phone ?: "") {

                    }
                }
            }

            Container(weight = 2f) {
                PrimaryButton(value = "Logout", color = ButtonColor.COLORFUL) {
                    TokenManager.clearToken()


                    //TODO start activity auth
                        //val intent = Intent(getContext()!!, AuthActivity::class.java)
                }
            }

        }
    }


}
