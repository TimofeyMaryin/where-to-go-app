package com.where.to.go.common_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.common_ui.values.TextFieldType
import com.where.to.go.common_ui.values.TextSize
import com.where.to.go.common_ui.values.TextWeight

enum class ContactType(val type: TextFieldType, val hint: String) {
    TG(TextFieldType.TEXT, "Введите свой ID"),
    VK(TextFieldType.TEXT, "Введите свой ID"),
    PHONE(TextFieldType.PHONE, "Введите номер телефона")
}

@Composable
fun SocialLink(
    @DrawableRes img: Int,
    value: String = "",
    onAdd: () -> Unit,
    end: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxWidth().height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    contentScale = ContentScale.Crop
                )

                TextButton(onClick = { if(value.isEmpty()) {onAdd()} }) {
                    AppText(
                        text = value.ifEmpty { stringResource(id = R.string.add) },
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE,
                        textDecoration = if(value.isEmpty()) {
                            TextDecoration.Underline
                        } else{
                            TextDecoration.None
                        }
                    )
                }

            }
            end()
        }
    }
}

@Composable
fun SocialLinkEditable(
    @DrawableRes img: Int,
    value: String = "",
    onAdd: () -> Unit,
    onEdit: () -> Unit
) {

    SocialLink(img = img, value,
        end = {
              Icon(
                  imageVector = Icons.Default.Edit,
                  contentDescription = "",
                  tint = Color.White.copy(alpha = 0.5f),
                  modifier = Modifier.clickable {  onEdit.invoke() })
        },
        onAdd = {onAdd.invoke()})
}

@Preview
@Composable
private fun SocialLinkPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        /**** Social link ****/
        SocialLink(img = R.drawable.ic_phone_rounded, "",
            end = {  },
            onAdd = {})

        /**** Social link with checkbox ****/
        SocialLink(img = R.drawable.ic_telergam_rounded, "",
            end = { SmallCheckBox(status = true) },
            onAdd = {})

        /**** Social link with button ****/
        SocialLinkEditable(img = R.drawable.ic_vk_rounded, "",
            onEdit = {},
            onAdd = {})

    }
}