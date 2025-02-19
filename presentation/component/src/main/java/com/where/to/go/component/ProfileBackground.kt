package com.where.to.go.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.offset

@Composable
fun ProfileBackground(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(
            color = colorContainerBg,
            shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
        )){
        /*Box(modifier = Modifier
            .fillMaxSize()
            .offset(x = -offset)
            .background(
                colorContainerBg,
                shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
            ))
        Box(modifier = Modifier
            .fillMaxSize()
            .absoluteOffset(x = offset)
            .background(
                colorContainerBg,
                shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
            ),
        )*/
    }
}