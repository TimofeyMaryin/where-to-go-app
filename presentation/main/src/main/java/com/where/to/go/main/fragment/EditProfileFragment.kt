package com.where.to.go.main.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.PersonalType
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.TextFieldType
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.animateIconColor
import com.where.to.go.component.animatedColorPrimary
import com.where.to.go.component.colorBg
import com.where.to.go.component.colorContainerBg
import com.where.to.go.component.primaryClip
import com.where.to.go.component.primaryFillWidth
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.main.R
import com.where.to.go.main.navigation.Screen
import com.where.to.go.main.utils.ImagePicker
import com.where.to.go.main.vms.ImageEditorViewModel
import com.where.to.go.main.vms.NavigationViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.UserDataChangedCallback
import com.yalantis.ucrop.UCrop
import java.io.File

@Composable
fun EditProfileFragment(
    profileViewModel: ProfileViewModel,
    editorViewModel: ImageEditorViewModel,
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
                    Log.e("MAIN", "encoded $encodedUri")
                    editorViewModel.setImageUri(uri)
                }
            }
        }
    }


    var openDialogFillPersonalData by remember { mutableStateOf<PersonalType?>(null) }
    var userPersonalData by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var totalWeightForBackgroundElement by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .collect { layoutInfo ->
                totalWeightForBackgroundElement = ((layoutInfo.visibleItemsInfo.sumOf { it.size } + lazyListState.firstVisibleItemScrollOffset.toFloat()) / 5000f)
                Log.e("TAG", "ProfileFragment totalWeightForBackgroundElement: $totalWeightForBackgroundElement", )
            }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f - totalWeightForBackgroundElement))

        Box(modifier = Modifier
            .clip(primaryClip())
            .fillMaxWidth(1f)
            .weight(7f)
            .fillMaxHeight()
            .background(
                colorContainerBg
            ))
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(primaryFillWidth),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = profileViewModel.loginUser?.avatar?.toIntOrNull() ?: R.drawable.avatar_placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopStart) // Align the image to the top
                            .clip(primaryClip())
                            .border(
                                width = 1.dp,
                                color = animatedColorPrimary(),
                                shape = primaryClip()
                            )
                            .size(100.dp)
                            .clickable {
                                imagePicker.pickImage(context as Activity, getImageLauncher)
                            },
                        contentScale = ContentScale.Crop
                    )

                    // The rest of your Row content can go here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart), // Align the Row to the bottom
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            AppText(
                                text = profileViewModel.loginUser?.name ?: "User${profileViewModel.loginUser?.id}",
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
                                    text = profileViewModel.loginUser?.region ?: "---",
                                    weight = TextWeight.REGULAR,
                                    size = TextSize.BODY_LARGE
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(1000.dp))
            }

        }

    }
}

private fun editImage(sourceUri: Uri, cropLauncher: ActivityResultLauncher<Intent>, context: Context) {
    val destinationUri = Uri.fromFile(File(context.cacheDir, "cropped_image.png"))
    val options = UCrop.Options().apply {
        setCompressionQuality(100)
    }
    UCrop.of(sourceUri, destinationUri)
        .withAspectRatio(1f, 1f)
        .withMaxResultSize(800, 800)
        .withOptions(options)
        .getIntent(context).also { intent ->
            cropLauncher.launch(intent) // Use the launcher to start the crop activity
        }
}