package com.where.to.go.main.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimateFragmentContainer(
    enable: Boolean,
    content: @Composable () -> Unit,
) {
    val prTween = 400

    AnimatedVisibility(
        visible = enable,
        enter = fadeIn(tween(prTween)),
        exit = fadeOut(tween(prTween))
    ) {
        content()
    }

}