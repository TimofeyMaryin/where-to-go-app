package com.where.to.go.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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
        fontWeight = weight.weight,
        fontSize = size.size,
        textAlign = textAlign,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        modifier = modifier,
        lineHeight = lineHeight?.sp ?: 28.sp,
        softWrap = true,
        overflow = TextOverflow.Visible
    )
}