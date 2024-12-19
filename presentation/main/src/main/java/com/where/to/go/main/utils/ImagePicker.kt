package com.where.to.go.main.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import java.io.InputStream

class ImagePicker {
    var imageBitmap = mutableStateOf<Bitmap?>(null)

    fun loadImage(uri: Uri, inputStream: InputStream) {
        imageBitmap.value = BitmapFactory.decodeStream(inputStream)
    }

    fun pickImage(activity: Activity, launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        launcher.launch(intent)
    }
}