package com.where.to.go.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.colorError
import com.where.to.go.component.values.colorWhite
import com.where.to.go.component.values.offset
import com.where.to.go.component.values.shortOffset

val size: Int = 55
@Composable
fun CustomSearchView(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onFiltersClick: () -> Unit,
    isError: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(size.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)){
            SearchTextField(
                hint = hint,
                value = value,
                onValueChange = onValueChange,
                isError = isError,
                modifier = Modifier.fillMaxSize()
            )
            Box(modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = offset)

            ){
                Icon(painter = painterResource(id = R.drawable.ic_filters),
                    contentDescription = "",
                    tint = Color.White.copy(alpha = 0.25f),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onFiltersClick.invoke()
                        }
                )
            }

        }


        SquareButton(icon = R.drawable.baseline_search_24, size = size.dp, iconSize = 34.dp) {
            onSearchClick()
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
            .fillMaxHeight(),
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
        placeholder = { Text(text = hint, color = Color.Gray) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        visualTransformation = VisualTransformation.None
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
            onSearchClick = {  },
            onFiltersClick = {  },
            isError = value.contains("1")
        )
    }
}