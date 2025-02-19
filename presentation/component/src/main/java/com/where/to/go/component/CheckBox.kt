package com.where.to.go.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.animateBrushPrimary
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorWhite

@Composable
fun AppCheckBox(
    status: Boolean,
    onClick: () -> Unit
) {
    val tweenMs = 500

    val animateBorderAlpha by animateFloatAsState(
        targetValue = if (status) 1f else 0f,
        animationSpec = tween(tweenMs)
    )

    Box(
        modifier = Modifier
            .clip(primaryClip())
            .border(
                width = 2.dp,
                brush = animateBrushPrimary(animateBorderAlpha),
                shape = primaryClip()
            )
            .background(colorBg)
            .size(40.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = status,
            enter = slideInVertically(tween(tweenMs)) + fadeIn(tween(tweenMs)),
            exit = slideOutVertically(tween(tweenMs)) + fadeOut(tween(tweenMs))
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colorWhite,
                modifier = Modifier.size(25.dp)
            )
        }
    }

}

@Composable
private fun AppCheckBox(
    status: Boolean,
) {
    val tweenMs = 500

    val animateBorderAlpha by animateFloatAsState(
        targetValue = if (status) 1f else 0f,
        animationSpec = tween(tweenMs)
    )

    Box(
        modifier = Modifier
            .clip(primaryClip())
            .border(
                width = 2.dp,
                brush = animateBrushPrimary(animateBorderAlpha),
                shape = primaryClip()
            )
            .background(colorBg)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = status,
            enter = slideInVertically(tween(tweenMs)) + fadeIn(tween(tweenMs)),
            exit = slideOutVertically(tween(tweenMs)) + fadeOut(tween(tweenMs))
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colorWhite,
                modifier = Modifier.size(25.dp)
            )
        }
    }

}


@Composable
fun CheckBoxParent(
    status: Boolean,
    value: String,
    onClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppCheckBox(status = status)

            AppText(text = value, weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE)
        }
    }

}


@Composable
fun SmallCheckBox(
    status: Boolean,
) {

    val tweenMs = 500

    val animateBorderAlpha by animateFloatAsState(
        targetValue = if (status) 1f else 0f,
        animationSpec = tween(tweenMs)
    )

    Box(
        modifier = Modifier.size(35.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    brush = animateBrushPrimary(animateBorderAlpha),
                    shape = CircleShape,
                )
                .background(colorBg)
                .size(20.dp),
        )
        AnimatedVisibility(
            visible = status,
            enter = slideInVertically(tween(tweenMs)) + fadeIn(tween(tweenMs)),
            exit = slideOutVertically(tween(tweenMs)) + fadeOut(tween(tweenMs))
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colorWhite,
                modifier = Modifier.size(30.dp)
            )
        }
    }
    
}

@Preview
@Composable
private fun CheckBoxPreview() {
    var state by remember { mutableStateOf(false) }

    CheckBoxParent(status = state, value = "Я посетитель") {
        state = !state
    }
}