package com.where.to.go.common_ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gufo.custom.gufoshadow.shadow
import com.where.to.go.common_ui.values.blue
import com.where.to.go.common_ui.values.pink

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

@Composable fun primaryClip() = MaterialTheme.shapes.large

@Composable fun largeClip() = MaterialTheme.shapes.extraLarge
@Composable fun secondaryClip() = MaterialTheme.shapes.small

@Composable
fun Modifier.primaryButtonShadow(): Modifier {
    val transition = rememberInfiniteTransition()
    val animateShadowColor by transition.animateColor(
        initialValue = pink,
        targetValue = blue,
        animationSpec = InfiniteRepeatableSpec(
            tween(400),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )


    return this.shadow(
        borderRadius = 16.dp,
        blurRadius = 15.dp,
        color = animateShadowColor
    )
}

@Composable
fun Modifier.toggleShadow(): Modifier {
    val transition = rememberInfiniteTransition()
    val animateShadowColor by transition.animateColor(
        initialValue = pink,
        targetValue = blue,
        animationSpec = InfiniteRepeatableSpec(
            tween(1000 * 5),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )


    return this.shadow(
        borderRadius = 5.dp,
        blurRadius = 40.dp,
        color = animateShadowColor
    )
}

class CustomSvgShape(private val pathData: String) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = PathParser().parsePathString(pathData).toPath()

        val originalWidth = 842f
        val originalHeight = 188f

        val scaleX = size.width / originalWidth
        val scaleY = size.height / originalHeight
        val matrix = Matrix()
        matrix.scale(scaleX, scaleY)
        path.transform(matrix)

        return Outline.Generic(path)
    }
}
