package com.where.to.go.component

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
import com.where.to.go.component.values.blue
import com.where.to.go.component.values.pink


@Composable fun primaryClip() = MaterialTheme.shapes.large
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

