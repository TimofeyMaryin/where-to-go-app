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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.where.to.go.component.AppText
import com.where.to.go.component.ProfileBackground
import com.where.to.go.component.largeClip
import com.where.to.go.component.values.animatedColorPrimary
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.offset
import com.where.to.go.component.values.shortOffset
import com.where.to.go.internet.models.RequestState
import com.where.to.go.main.R
import com.where.to.go.main.utils.ImagePicker
import com.where.to.go.main.vms.EditProfileViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.yalantis.ucrop.UCrop
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@Composable
fun EditProfileFragment(
    profileViewModel: ProfileViewModel,
    viewModel: EditProfileViewModel
) {
    val context = LocalContext.current
    val imagePicker = remember { ImagePicker() }

    val uploadAvatarState by viewModel.uploadAvatarState.observeAsState(RequestState())

    when {
        uploadAvatarState.isLoading -> {
            // Показать индикатор загрузки
        }
        uploadAvatarState.error != null -> {
            Toast.makeText(context, uploadAvatarState.error, Toast.LENGTH_LONG).show()
        }
        uploadAvatarState.data != null -> {
            Toast.makeText(context, "УСПЕШНО ${uploadAvatarState.data!!.response}", Toast.LENGTH_LONG).show()
        }
    }

    val cropLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val resultUri = UCrop.getOutput(result.data!!)
            resultUri?.let {
                val file = MultipartBody.Part.createFormData(
                    viewModel.loginUser!!.email,
                    "${viewModel.loginUser!!.email}.jpg",
                    RequestBody.create(MediaType.parse("image/jpeg"), File(resultUri.path!!))
                )
                viewModel.uploadAvatar(file)
            }
        } else if (result.resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(result.data!!)
        }
    }

    val getImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            uri?.let {
                context.contentResolver.openInputStream(it)?.let { inputStream ->
                    imagePicker.loadImage(it, inputStream)
                    editImage(uri, cropLauncher, context)
                }
            }
        }
    }

    val lazyListState = rememberLazyListState()
    var totalWeightForBackgroundElement by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .collect { layoutInfo ->
                totalWeightForBackgroundElement = ((layoutInfo.visibleItemsInfo.sumOf { it.size } + lazyListState.firstVisibleItemScrollOffset.toFloat()) / 5000f)
                Log.e("TAG", "ProfileFragment totalWeightForBackgroundElement: $totalWeightForBackgroundElement", )
            }

    }
    val avatarSize = 140.dp
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
                    .clip(largeClip())
                    .border(
                        width = 1.dp,
                        color = animatedColorPrimary(),
                        shape = largeClip()
                    )
                    .size(avatarSize)
                    .clickable {
                        imagePicker.pickImage(context as Activity, getImageLauncher)
                    },
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
                        size = TextSize.TITLE
                    )
                    AppText(
                        text = "status: ${1}",
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE
                    )

                    Spacer(Modifier.height(30.dp))
                }
                Row(
                    modifier = Modifier
                        .padding(top = paddingTop - 4.dp, start = shortOffset)
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
        Spacer(Modifier.height(20.dp))
        AppText(
            text =  stringResource(id = R.string.about),
            weight = TextWeight.BOLD,
            size = TextSize.TITLE
        )
        /*ProfilePersonalData(
            theme = stringResource(id = R.string.about),
            value = viewModel.loginUser?.status ?: "---"
        )*/
        viewModel.loginUser?.description?.let { AppText(text = it) }
    }

/*
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

    }*/
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
            cropLauncher.launch(intent)
        }
}