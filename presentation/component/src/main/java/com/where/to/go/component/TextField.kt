package com.where.to.go.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.colorError
import com.where.to.go.component.values.colorWhite
import kotlin.math.absoluteValue


enum class TextFieldType { TEXT, PASSWORD, EMAIL, PHONE }

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
                keyboardType = when (type) {
                    TextFieldType.TEXT -> KeyboardType.Text
                    TextFieldType.PASSWORD -> KeyboardType.Password
                    TextFieldType.EMAIL -> KeyboardType.Email
                    TextFieldType.PHONE -> KeyboardType.Number
                }
            ),
            visualTransformation = when (type) {
                TextFieldType.TEXT -> VisualTransformation.None
                TextFieldType.PASSWORD -> PasswordVisualTransformation()
                TextFieldType.EMAIL -> VisualTransformation.None
                TextFieldType.PHONE -> MaskVisualTransformation("# (###) ###-##-##")
            }
        )
    }

}



@Preview
@Composable
private fun AppTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBg),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier.fillMaxSize(.9f), contentAlignment = Alignment.Center) {
            AppTextField(hint = "E-mail", value = value, isError = value.contains("1"), type = TextFieldType.PASSWORD) {
                value = it
            }
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
