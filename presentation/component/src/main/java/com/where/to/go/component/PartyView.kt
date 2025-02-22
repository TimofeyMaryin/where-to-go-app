package com.where.to.go.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.toolbox.ImageRequest
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorBg
import com.where.to.go.internet.models.Party

@Composable
fun PartyView(
    party: Party,
    modifier: Modifier = Modifier,
    onClick: (Party) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = {onClick(party)})
            .padding(start = 7.dp, end = 7.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        WebImage(url = party.image,
            placeholderColor = Color.Gray, modifier = Modifier.height(100.dp)
                .width(128.dp).align(Alignment.CenterStart).clip(RoundedCornerShape(10.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 136.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            AppText(
                text = party.name,
                modifier = Modifier.padding(bottom = 4.dp),
                weight = TextWeight.BOLD,
                size = TextSize.TITLE,
            )
            AppText(
                text = party.date,
                modifier = Modifier.padding(bottom = 8.dp),
                weight = TextWeight.REGULAR,
                size = TextSize.BODY,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF27273F))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    AppText(
                        text = "${party.maxGuests} гостей",
                        weight = TextWeight.MEDIUM,
                        size = TextSize.BODY,
                    )
                }
                AppText(
                    text = party.price.toString(),
                    weight = TextWeight.MEDIUM,
                    size = TextSize.TITLE,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopEnd),
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



@Composable
fun LargePartyView(
    party: Party,
    modifier: Modifier = Modifier,
    onClick: (Party) -> Unit
) {
    Box(
        modifier = modifier
            .height(300.dp)
            .width(360.dp)
            .clickable(onClick = {onClick(party)})
            .padding(start = 7.dp, end = 7.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        WebImage(url = party.image,
            placeholderColor = Color.Gray,
            )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            AppText(
                text = party.name,
                modifier = Modifier.padding(bottom = 4.dp),
                weight = TextWeight.BOLD,
                size = TextSize.TITLE,
            )
            AppText(
                text = party.date,
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
                Box(
                    modifier = Modifier
                        .background(Color(0xFF27273F))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    AppText(
                        text = "${party.maxGuests} гостей",
                        weight = TextWeight.MEDIUM,
                        size = TextSize.BODY_LARGE,
                    )
                }
                AppText(
                    text = party.price.toString(),
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
private fun PartyViewPreview() {
    val party = Party(
        id = 1,
        ownerId = 1001,
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTRgUPG8RubS8Z3lAG1lGlwJyudTbg6KXoeEyfQHe0QktRH3ahrGyMI0FnK0tNGPuUd0w&usqp=CAU",
        about = "Незабываемая вечеринка на крыше с видом на город!",
        tg = "@RoofParty",
        price = 1500,
        theme = "Neon Nights",
        name = "Ночная тусовка",
        date = "2025-03-01T20:00:00",
        maxGuests = 50,
        status = 1,
        created = "2025-02-21T10:00:00"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBg),
        verticalArrangement = Arrangement.Center,
    ) {
        LargePartyView(party){}
    }
}