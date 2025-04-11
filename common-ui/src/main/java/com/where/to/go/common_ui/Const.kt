package com.where.to.go.common_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

const val primaryFillWidth = 0.9f

@Composable
fun getScreenWidthInDp(): Float {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    return displayMetrics.widthPixels / displayMetrics.density
}