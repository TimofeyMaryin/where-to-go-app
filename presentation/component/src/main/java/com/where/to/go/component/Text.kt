package com.where.to.go.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorWhite
import com.where.to.go.component.values.fontFamily


@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = colorWhite,
    weight: TextWeight = TextWeight.MEDIUM,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    size: TextSize = TextSize.BODY_LARGE,
    lineHeight: Int? = null
) {
    Text(
        text = text,
        color = color,
        fontWeight = when (weight) {
            TextWeight.REGULAR -> FontWeight.Normal
            TextWeight.MEDIUM -> FontWeight.Medium
            TextWeight.BOLD -> FontWeight.Bold
        },
        fontSize = when (size) {
            TextSize.HEADLINE -> 38.sp
            TextSize.TITLE_LARGE -> 28.sp
            TextSize.TITLE -> 20.sp
            TextSize.BODY_LARGE -> 14.sp
        },
        textAlign = textAlign,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        modifier = modifier,
        lineHeight = lineHeight?.sp ?: 28.sp
    )
}