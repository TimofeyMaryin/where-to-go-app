package com.where.to.go.component

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun WebImage(
    url: String,
    placeholderColor: Color = Color.Gray,
    modifier: Modifier = Modifier
) {
    val painterState = remember { mutableStateOf<Painter?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    val imageLoadingDispatcher = remember {
        Dispatchers.IO.limitedParallelism(5)
    }

    LaunchedEffect(url) {
        withContext(imageLoadingDispatcher) {
            var connection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            try {
                connection = URL(url).openConnection() as HttpURLConnection
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                connection.doInput = true
                connection.connect()

                inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                bitmap?.let {
                    painterState.value = BitmapPainter(it.asImageBitmap())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
                connection?.disconnect()
                isLoading.value = false
            }
        }
    }

    Box(modifier = modifier) {
        Image(
            painter = painterState.value ?: ColorPainter(placeholderColor),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}