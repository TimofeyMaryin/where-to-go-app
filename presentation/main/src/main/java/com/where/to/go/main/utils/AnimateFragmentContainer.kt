package com.where.to.go.main.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
fun AnimateFragmentContainer(
    enable: Boolean,
    prTween: Int = 400,
    content: @Composable () -> Unit,
) {

    AnimatedVisibility(
        visible = enable,
        enter = scaleIn(initialScale = 0.8f, animationSpec = tween(prTween)) + fadeIn(),
        exit = scaleOut(targetScale = 1.2f, animationSpec = tween(prTween)) + fadeOut()
    ) {
        content()
    }
}