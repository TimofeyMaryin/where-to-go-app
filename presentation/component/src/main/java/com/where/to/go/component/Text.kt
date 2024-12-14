package com.where.to.go.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val fontFamily = FontFamily(
    listOf(
        Font(R.font.montserrat_regular, weight = FontWeight.Normal),
        Font(R.font.montserrat_bold, weight = FontWeight.Bold),
        Font(R.font.montserrat_medium, weight = FontWeight.Medium)
    )
)

enum class TextWeight { REGULAR, MEDIUM, BOLD }
enum class TextSize { HEADLINE, TITLE_LARGE, TITLE_MEDIUM, BODY_LARGE }

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = colorWhite,
    weight: TextWeight,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    size: TextSize,
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
            TextSize.TITLE_MEDIUM -> 20.sp
            TextSize.BODY_LARGE -> 14.sp
        },
        textAlign = textAlign,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        modifier = modifier,
        lineHeight = lineHeight?.sp ?: 28.sp
    )
}