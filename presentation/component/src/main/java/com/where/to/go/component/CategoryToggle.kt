package com.where.to.go.component

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun CategoryToggle(
    isChecked: Boolean,
    onToggle: (Boolean) -> Unit,
    label: String
) {
    val backgroundColor = if (isChecked) pink else Color.Transparent
    val borderColor = if (isChecked) Color.Transparent else pink // Define your border color
    val textColor = colorWhite // Define your text color
    Box(
        modifier = Modifier
             .padding(8.dp)
            .border(2.dp, borderColor, shape = RoundedCornerShape(10.dp))
            .background(backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable { onToggle(!isChecked) }
            .height(35.dp)
            .fillMaxWidth()
            .then(if (isChecked) Modifier.toggleShadow() else Modifier),// Fill the width of the parent
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text = label,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            weight = TextWeight.REGULAR,
            size = TextSize.BODY_LARGE,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun CategoryTogglePreview() {
    var isChecked by remember { mutableStateOf(false) }

    CategoryToggle(
        isChecked = isChecked,
        onToggle = { isChecked = it },
        label = "Category"
    )
}
