package com.where.to.go.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.where.to.go.internet.models.Party

@Composable
fun PartyFilters(
    modifier: Modifier = Modifier,
    onApply: () -> Unit
)  {
    Box(modifier = Modifier.fillMaxSize())
}