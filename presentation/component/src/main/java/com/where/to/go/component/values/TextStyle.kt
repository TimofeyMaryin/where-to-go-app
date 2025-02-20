package com.where.to.go.component.values

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.where.to.go.component.R

val fontFamily = FontFamily(
    listOf(
        Font(R.font.montserrat_regular, weight = FontWeight.Normal),
        Font(R.font.montserrat_bold, weight = FontWeight.Bold),
        Font(R.font.montserrat_medium, weight = FontWeight.Medium)
    )
)
enum class TextWeight { REGULAR, MEDIUM, BOLD }
enum class TextSize { HEADLINE, TITLE_LARGE, TITLE, BODY_LARGE }