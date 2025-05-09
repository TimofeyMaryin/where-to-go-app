package com.where.to.go.common_ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.where.to.go.common_ui.values.TextSize
import com.where.to.go.common_ui.values.TextWeight
import kotlinx.coroutines.delay

@Composable
fun AnimateText(
    value: String,
    weight: TextWeight,
    size: TextSize,
) {
    val animatedText = remember { Animatable(0f) }
    val fullText = value.toCharArray()
    LaunchedEffect(Unit) {
        for (i in fullText.indices) {
            animatedText.animateTo(
                targetValue = (i + 1).toFloat(),
                animationSpec = tween(durationMillis = 100)
            )
            delay(100)
        }
    }


    AppText(text = fullText.take(animatedText.value.toInt()).joinToString(""), weight = weight, size = size)
}