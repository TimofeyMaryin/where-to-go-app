package com.where.to.go.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val size: Int = 55
@Composable
fun CustomSearchView(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    isError: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(end = 10.dp, start = 10.dp)
            .height(size.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Search TextField
        SearchTextField(
            hint = hint,
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            modifier = Modifier.weight(1f) // Take remaining space
        )

        // Search Button
        SquareButton(icon = R.drawable.ic_launcher_foreground, size = size.dp) {
            onSearchClick() // Handle search action
        }
    }
}

@Composable
fun SearchTextField(
    hint: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 2.dp,
                color = if (isError) colorError else Color.Transparent,
                shape = primaryClip()
            )
            .padding(end = 10.dp)
            .fillMaxHeight(), // Fill height of the parent
        shape = primaryClip(),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,

            focusedContainerColor = colorContainerBg,
            errorContainerColor = colorContainerBg,
            disabledContainerColor = colorContainerBg,
            unfocusedContainerColor = colorContainerBg,

            unfocusedTextColor = colorWhite,
            focusedTextColor = colorWhite,
            disabledTextColor = colorWhite,
            errorTextColor = colorError,
        ),
        maxLines = 1,
        singleLine = true,
        isError = isError,
        placeholder = { Text(text = hint, color = Color.Gray) }, // Hint inside TextField
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text // Only handling text input
        ),
        visualTransformation = VisualTransformation.None
    // No transformation for text input
    )
}

@Preview
@Composable
private fun SearchViewPreview() {
    var value by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBg),
        contentAlignment = Alignment.Center,
    ) {
        CustomSearchView(
            hint = "Search...",
            value = value,
            onValueChange = { value = it },
            onSearchClick = { /* Handle search action */ },
            isError = value.contains("1") // Example error condition
        )
    }
}