package com.where.to.go.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.TextFieldType
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.colorError
import com.where.to.go.component.values.colorWhite
import com.where.to.go.component.values.offset
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.absoluteValue



@Composable
fun AppTextField(
    hint: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    value: String,
    type: TextFieldType,
    onValueChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText(
            text = hint,
            weight = TextWeight.REGULAR,
            size = TextSize.BODY_LARGE,
            color = if (isError) colorError else colorWhite
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = if (isError) colorError else Color.Transparent,
                    shape = primaryClip()
                )
                .fillMaxWidth()
                .then(modifier),
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
            keyboardOptions = KeyboardOptions(
                keyboardType = type.keyboardType
            ),
            visualTransformation = type.visualTransformation
        )
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun AppRangeTextField(
    hint: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    range: Pair<String, String>,
    type: TextFieldType = TextFieldType.NUMBER,
    onRangeChange: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText(
            text = hint,
            weight = TextWeight.REGULAR,
            size = TextSize.BODY_LARGE,
            color = if (isError) colorError else colorWhite
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Состояние для отображения DatePicker для "От"
            val showFromDatePicker = remember { mutableStateOf(false) }
            // Состояние для отображения DatePicker для "До"
            val showToDatePicker = remember { mutableStateOf(false) }

            // Formatter для преобразования даты в "DD.MM.YYYY"
            val dateFormatter = SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault())

            if (type == TextFieldType.DATE) {
                // Поле "От" с DatePicker
                TextField(
                    value = range.first,
                    onValueChange = {}, // Поле только для чтения
                    readOnly = true, // Делаем поле только для чтения
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = if (isError) colorError else Color.Transparent,
                            shape = primaryClip()
                        )
                        .clickable { showFromDatePicker.value = true }, // Открываем DatePicker по клику
                    shape = primaryClip(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorBg,
                        errorContainerColor = colorBg,
                        disabledContainerColor = colorBg,
                        unfocusedContainerColor = colorBg,
                        unfocusedTextColor = colorWhite,
                        focusedTextColor = colorWhite,
                        disabledTextColor = colorWhite,
                        errorTextColor = colorError,
                        cursorColor = colorWhite,
                        unfocusedPlaceholderColor = colorWhite.copy(alpha = 0.5f),
                        focusedPlaceholderColor = colorWhite.copy(alpha = 0.5f)
                    ),
                    maxLines = 1,
                    singleLine = true,
                    isError = isError,
                    textStyle = TextStyle(
                        color = colorWhite,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            text = "DD.MM.YYYY",
                            color = colorWhite.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )

                // DatePicker для "От"
                if (showFromDatePicker.value) {
                    val datePickerState = rememberDatePickerState()
                    DatePickerDialog(
                        onDismissRequest = { showFromDatePicker.value = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    val selectedDate = datePickerState.selectedDateMillis
                                    if (selectedDate != null) {
                                        val formattedDate = dateFormatter.format(Date(selectedDate))
                                        onRangeChange(formattedDate, range.second)
                                    }
                                    showFromDatePicker.value = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showFromDatePicker.value = false }) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            } else {
                // Обычное поле для других типов
                TextField(
                    value = range.first,
                    onValueChange = { newValue ->
                        val filteredValue = when (type) {
                            TextFieldType.NUMBER -> newValue.filter { it.isDigit() }.take(10)
                            else -> newValue.take(10)
                        }
                        onRangeChange(filteredValue, range.second)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = if (isError) colorError else Color.Transparent,
                            shape = primaryClip()
                        ),
                    shape = primaryClip(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorBg,
                        errorContainerColor = colorBg,
                        disabledContainerColor = colorBg,
                        unfocusedContainerColor = colorBg,
                        unfocusedTextColor = colorWhite,
                        focusedTextColor = colorWhite,
                        disabledTextColor = colorWhite,
                        errorTextColor = colorError,
                        cursorColor = colorWhite,
                        unfocusedPlaceholderColor = colorWhite.copy(alpha = 0.5f),
                        focusedPlaceholderColor = colorWhite.copy(alpha = 0.5f)
                    ),
                    maxLines = 1,
                    singleLine = true,
                    isError = isError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = type.keyboardType
                    ),
                    visualTransformation = type.visualTransformation,
                    textStyle = TextStyle(
                        color = colorWhite,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            text = "От",
                            color = colorWhite.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.width(offset))

            if (type == TextFieldType.DATE) {
                // Поле "До" с DatePicker
                TextField(
                    value = range.second,
                    onValueChange = {}, // Поле только для чтения
                    readOnly = true, // Делаем поле только для чтения
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = if (isError) colorError else Color.Transparent,
                            shape = primaryClip()
                        )
                        .clickable { showToDatePicker.value = true }, // Открываем DatePicker по клику
                    shape = primaryClip(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorBg,
                        errorContainerColor = colorBg,
                        disabledContainerColor = colorBg,
                        unfocusedContainerColor = colorBg,
                        unfocusedTextColor = colorWhite,
                        focusedTextColor = colorWhite,
                        disabledTextColor = colorWhite,
                        errorTextColor = colorError,
                        cursorColor = colorWhite,
                        unfocusedPlaceholderColor = colorWhite.copy(alpha = 0.5f),
                        focusedPlaceholderColor = colorWhite.copy(alpha = 0.5f)
                    ),
                    maxLines = 1,
                    singleLine = true,
                    isError = isError,
                    textStyle = TextStyle(
                        color = colorWhite,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            text = "DD.MM.YYYY",
                            color = colorWhite.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )

                // DatePicker для "До"
                if (showToDatePicker.value) {
                    val datePickerState = rememberDatePickerState()
                    DatePickerDialog(
                        onDismissRequest = { showToDatePicker.value = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    val selectedDate = datePickerState.selectedDateMillis
                                    if (selectedDate != null) {
                                        val formattedDate = dateFormatter.format(Date(selectedDate))
                                        onRangeChange(range.first, formattedDate)
                                    }
                                    showToDatePicker.value = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showToDatePicker.value = false }) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            } else {
                // Обычное поле для других типов
                TextField(
                    value = range.second,
                    onValueChange = { newValue ->
                        val filteredValue = when (type) {
                            TextFieldType.NUMBER -> newValue.filter { it.isDigit() }.take(10)
                            else -> newValue.take(10)
                        }
                        onRangeChange(range.first, filteredValue)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = if (isError) colorError else Color.Transparent,
                            shape = primaryClip()
                        ),
                    shape = primaryClip(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorBg,
                        errorContainerColor = colorBg,
                        disabledContainerColor = colorBg,
                        unfocusedContainerColor = colorBg,
                        unfocusedTextColor = colorWhite,
                        focusedTextColor = colorWhite,
                        disabledTextColor = colorWhite,
                        errorTextColor = colorError,
                        cursorColor = colorWhite,
                        unfocusedPlaceholderColor = colorWhite.copy(alpha = 0.5f),
                        focusedPlaceholderColor = colorWhite.copy(alpha = 0.5f)
                    ),
                    maxLines = 1,
                    singleLine = true,
                    isError = isError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = type.keyboardType
                    ),
                    visualTransformation = type.visualTransformation,
                    textStyle = TextStyle(
                        color = colorWhite,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            text = "До",
                            color = colorWhite.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}
@Composable
fun TextFieldToggle(
    modifier: Modifier = Modifier.height(20.dp),
    value: String,
    size: TextSize = TextSize.BODY_LARGE,
    weight: TextWeight = TextWeight.REGULAR,
    isError: Boolean = false,
    onSave: (String) -> Unit){
    var edit by remember { mutableStateOf(false) }
    var cValue by remember { mutableStateOf(value) }
    Box (modifier = modifier
        .fillMaxWidth()){
        if(edit){
            TextField(

                value = cValue,
                onValueChange = { newValue ->
                    cValue = newValue
                },
                modifier = modifier
                    .border(
                        width = 2.dp,
                        color = if (isError) colorError else Color.Transparent,
                        shape = primaryClip()
                    )
                    .fillMaxWidth().padding(end = 26.dp).height(50.dp),
                shape = primaryClip(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,

                    focusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,

                    unfocusedTextColor = colorWhite,
                    focusedTextColor = colorWhite,
                    disabledTextColor = colorWhite,
                    errorTextColor = colorError,
                ),
                maxLines = 3,
                singleLine = false,
                isError = isError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardOptions.Default.keyboardType
                ),
                visualTransformation = VisualTransformation.None,
                textStyle = TextStyle(fontSize = size.size, fontWeight = weight.weight),
            )
            Icon(imageVector = Icons.Rounded.Check,
                contentDescription = "",
                tint = Color.White.copy(alpha = 0.45f),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd).clickable {
                        edit = false
                        onSave.invoke(cValue)
                    })

        }else{
            AppText(text = value,
                modifier = Modifier.align(Alignment.CenterStart).padding(end = 26.dp),
                textAlign = TextAlign.Left,
                size = size,
                weight = weight,
                lineHeight = 12)
            Icon(imageVector = Icons.Rounded.Edit,
                contentDescription = "",
                tint = Color.White.copy(alpha = 0.45f),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        edit = true
                    })

        }


    }

}


@Preview
@Composable
private fun AppTextFieldPreview() {
    var value by remember { mutableStateOf("ddd") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBg),
        contentAlignment = Alignment.Center,
    ) {
        Column(modifier = Modifier.fillMaxSize(.9f), verticalArrangement = Arrangement.Center) {
            AppTextField(hint = "E-mail", value = value, isError = value.contains("1"), type = TextFieldType.PASSWORD) {
                value = it
            }
            TextFieldToggle(value = value, onSave = {},
                modifier = Modifier.wrapContentHeight(),
                size = TextSize.TITLE)
            AppRangeTextField(
                hint = "",
                range = Pair("0", "100"),
                onRangeChange = {f, s -> },
                type = TextFieldType.NUMBER,
                modifier = Modifier
            )
            AppRangeTextField(
                hint = "",
                range = Pair("1111", "10.10.1000"),
                onRangeChange = {f, s -> },
                type = TextFieldType.DATE,
                modifier = Modifier
            )
        }
    }

}


class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}
