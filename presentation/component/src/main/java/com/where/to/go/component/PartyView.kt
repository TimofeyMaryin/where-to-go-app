package com.where.to.go.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor

@Composable
fun LargsePartyView(
    title: String,
    date: String,
    guestCount: Int,
    price: String,
    backgroundImageResId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(285.dp) // Высота контейнера
            .width(350.dp)
            .clickable(onClick = onClick) // Обработчик клика на весь контейнер
            .padding(start = 7.dp, end = 7.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = backgroundImageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Затемнение
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 1.8f))
                    )
                )
                .clip(RoundedCornerShape(25.dp))
        )

        // Содержимое контейнера
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            AppText(
                text = title,
                modifier = Modifier.padding(bottom = 4.dp),
                weight = TextWeight.BOLD,
                size = TextSize.TITLE_MEDIUM,
            )
            AppText(
                text = date,
                modifier = Modifier.padding(bottom = 8.dp),
                weight = TextWeight.REGULAR,
                size = TextSize.BODY_LARGE,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Контейнер с количеством гостей
                Box(
                    modifier = Modifier
                        .background(Color(0xFF27273F))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    AppText(
                        text = "$guestCount гостей",
                        weight = TextWeight.MEDIUM,
                        size = TextSize.BODY_LARGE,
                    )
                }
                AppText(
                    text = price,
                    weight = TextWeight.MEDIUM,
                    size = TextSize.TITLE_LARGE,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(55.dp)
                .background(color = Color(0xFF27273F), shape = MaterialTheme.shapes.medium)
                .align(Alignment.TopEnd),
            //.clickable { /* TODO: Handle image click */ },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart_full), // Замените на ваше изображение
                contentDescription = null,
                modifier = Modifier.size(23.dp)
            )
        }
    }
}

@Composable
fun LargePartyView(
    title: String,
    date: String,
    guestCount: Int,
    price: String,
    backgroundImageResId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(285.dp) // Высота контейнера
            .width(350.dp)
            .clickable(onClick = onClick) // Обработчик клика на весь контейнер
            .padding(start = 7.dp, end = 7.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = backgroundImageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Затемнение
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 1.8f))
                    )
                )
                .clip(RoundedCornerShape(25.dp))
        )

        // Содержимое контейнера
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            AppText(
                text = title,
                modifier = Modifier.padding(bottom = 4.dp),
                weight = TextWeight.BOLD,
                size = TextSize.TITLE_MEDIUM,
            )
            AppText(
                text = date,
                modifier = Modifier.padding(bottom = 8.dp),
                weight = TextWeight.REGULAR,
                size = TextSize.BODY_LARGE,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Контейнер с количеством гостей
                Box(
                    modifier = Modifier
                        .background(Color(0xFF27273F))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    AppText(
                        text = "$guestCount гостей",
                        weight = TextWeight.MEDIUM,
                        size = TextSize.BODY_LARGE,
                    )
                }
                AppText(
                    text = price,
                    weight = TextWeight.MEDIUM,
                    size = TextSize.TITLE_LARGE,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(55.dp)
                .background(color = Color(0xFF27273F), shape = MaterialTheme.shapes.medium)
                .align(Alignment.TopEnd),
                //.clickable { /* TODO: Handle image click */ },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart_full),
                contentDescription = null,
                modifier = Modifier.size(23.dp)
            )
        }
    }
}

@Preview
@Composable
fun PartyViewPreview() {
    LargePartyView(
        title = "Название события",
        date = "12 декабря 2024",
        guestCount = 5,
        price = "$100",
        backgroundImageResId = R.drawable.test,
        onClick = {}
    )
}