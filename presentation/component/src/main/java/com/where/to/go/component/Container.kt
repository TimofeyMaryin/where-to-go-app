package com.where.to.go.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.Container(
    weight: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(weight)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun RowScope.Container(
    weight: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(weight)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}