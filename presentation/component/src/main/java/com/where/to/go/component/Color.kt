package com.where.to.go.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

val colorBg = Color(0xff1e2031)
val colorBgBrush = Brush.horizontalGradient(colors = listOf(colorBg, colorBg))
val colorContainerBg = Color(0xff27273F)

val pink = Color(0xffF03CD6)
val blue = Color(0xff778CF9)

val brushPrimary = Brush.linearGradient(
        listOf(
            Color(0xffF03CD6),
            Color(0xff778CF9)
        )
    )

val colorWhite = Color.White
val colorGray = Color(0xff707387)

val colorError = Color(0xfff83133)


@Composable
fun animateBrushPrimary(alpha: Float = 1f): Brush {
    val transition = rememberInfiniteTransition()
    val colorOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val colors = listOf(
        Color(0xffF03CD6).copy(alpha),
        Color(0xff778CF9).copy(alpha),
        Color(0xff778CF9).copy(alpha),
        Color(0xffF03CD6).copy(alpha)
    )
    val gradient = Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = Offset(1000f * colorOffset, 1000f * colorOffset)
    )

    return gradient
}


