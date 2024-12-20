package com.where.to.go.main.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.where.to.go.main.vms.ImageEditorViewModel
import com.yalantis.ucrop.UCrop
import java.io.File
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ImageEditorFragment(
    viewModel: ImageEditorViewModel
) {
    val imageUri by viewModel.imageUri.observeAsState() // Наблюдаем за изменениями в URI

    val context = LocalContext.current

    val cropLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val resultUri = UCrop.getOutput(result.data!!)
            resultUri?.let {
                // Обработка результата обрезки
                // Например, можно обновить ViewModel или выполнить навигацию
            }
        } else if (result.resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(result.data!!)
            // Обработка ошибок
        }
    }

    // UI-компоненты
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imageUri != null) {
            Image(
                bitmap = BitmapFactory.decodeFile(imageUri!!.path).asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        } else {
            Text("Изображение не выбрано")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            imageUri?.let { startCrop(imageUri!!, cropLauncher, context) }
        }) {
            Text("Обрезать изображение")
        }
    }
}

private fun startCrop(sourceUri: Uri, cropLauncher: ActivityResultLauncher<Intent>, context: Context) {
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