package com.where.to.go.common_ui.values

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.where.to.go.common_ui.MaskVisualTransformation
import com.where.to.go.common_ui.R

val fontFamily = FontFamily(
    listOf(
        Font(R.font.montserrat_regular, weight = FontWeight.Normal),
        Font(R.font.montserrat_bold, weight = FontWeight.Bold),
        Font(R.font.montserrat_medium, weight = FontWeight.Medium)
    )
)

enum class TextWeight(val weight: FontWeight) {
    REGULAR(FontWeight.Normal),
    MEDIUM(FontWeight.Medium),
    BOLD(FontWeight.Bold),
}
enum class TextSize(val size: TextUnit) {
    HEADLINE(24.sp),
    TITLE(16.sp),
    TITLE_LARGE(20.sp),
    BODY(11.sp),
    BODY_LARGE(14.sp)
}

enum class TextFieldType(val visualTransformation: VisualTransformation, val keyboardType: KeyboardType, val length: Int) {
    TEXT(VisualTransformation.None, KeyboardType.Text, 255),
    PASSWORD(PasswordVisualTransformation(), KeyboardType.Password, 255),
    EMAIL( VisualTransformation.None, KeyboardType.Email, 255),
    PHONE(MaskVisualTransformation("# (###) ###-##-##"), KeyboardType.Number, 15),
    NUMBER(MaskVisualTransformation(""), KeyboardType.Number, 10),
    DATE(MaskVisualTransformation("##.##.####"), KeyboardType.Number, 10)
}


