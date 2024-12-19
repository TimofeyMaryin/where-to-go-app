package com.where.to.go.main.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.where.to.go.main.R
import com.where.to.go.main.vms.ImageEditorViewModel
import com.yalantis.ucrop.UCrop
import java.io.File

class ImageEditorFragment(private val viewModel: ImageEditorViewModel,
                          private val onCropComplete: (Uri) -> Unit) : Fragment() {

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageUri = Uri.parse(viewModel.imageUri)
        Log.e("EBOY", "TEST")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EditorScreen(imageUri) { uri ->
                    // Возвращаем обрезанное изображение
                    val resultIntent = Intent().apply {
                        putExtra("editedImageUri", uri.toString())
                    }
                    requireActivity().setResult(Activity.RESULT_OK, resultIntent)
                    requireActivity().finish()
                }
            }
        }
    }

    @Composable
    fun EditorScreen(imageUri: Uri?, onCropComplete: (Uri) -> Unit) {
        val isCropping by remember { mutableStateOf(false) }
        LaunchedEffect(imageUri) {
            imageUri?.let {
                startCrop(it, onCropComplete)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            imageUri?.let { uri ->
                Image(
                    bitmap = BitmapFactory.decodeFile(uri.path).asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { imageUri?.let { startCrop(it, onCropComplete) } }) {
                Text("Обрезать изображение")
            }
        }
    }

    private fun startCrop(sourceUri: Uri, onCropComplete: (Uri) -> Unit) {
        val destinationUri = Uri.fromFile(File(requireContext().cacheDir, "cropped_image.png"))
        UCrop.of(sourceUri, destinationUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(800, 800)
            .start(requireActivity(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val resultUri = UCrop.getOutput(data!!)
            resultUri?.let {
                // Передаем результат обрезки
                onCropComplete(it)
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            // Обработка ошибок
        }
    }
}