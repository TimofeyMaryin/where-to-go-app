package com.where.to.go.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ButtonColor { COLORFUL, BORDER }

@Composable
fun PrimaryButton(
    value: String,
    color: ButtonColor,
    onClick: () -> Unit
) {


    Box(
        modifier = Modifier
            .primaryButtonShadow()
            .clip(primaryClip())
            .border(
                when (color) {
                    ButtonColor.COLORFUL -> BorderStroke(width = 2.dp, color = Color.Transparent)
                    ButtonColor.BORDER -> BorderStroke(2.dp, animateBrushPrimary())
                },
                shape = primaryClip()
            )
            .height(70.dp)
            .aspectRatio(2.2f)
            .background(
                when (color) {
                    ButtonColor.COLORFUL -> animateBrushPrimary()
                    ButtonColor.BORDER -> colorBgBrush
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text = value,
            weight = TextWeight.MEDIUM,
            size = TextSize.BODY_LARGE
        )
    }

}

@Composable
fun SquareButton(
    size: Dp = 40.dp,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .clip(secondaryClip())
            .size(size)
            .background(animateBrushPrimary())
            .aspectRatio(1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = colorBg,
            modifier = Modifier.size(25.dp)
        )
    }

}

@Preview
@Composable
private fun PrimaryButtonPreview() {

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        PrimaryButton(value = "Начать", color = ButtonColor.COLORFUL) {

        }

        PrimaryButton(value = "Не Начать", color = ButtonColor.BORDER) {

        }
    }

}
