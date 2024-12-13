package com.where.to.go.main.fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gufo.custom.gufoshadow.shadow
import com.where.to.go.component.AddPersonalData
import com.where.to.go.component.AppText
import com.where.to.go.component.Container
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.animatedColorPrimary
import com.where.to.go.component.colorContainerBg
import com.where.to.go.component.primaryClip
import com.where.to.go.main.R
import com.where.to.go.main.vms.RecommendedViewModel

@Composable
fun ProfileFragment(
    navController: NavController,
    viewModel: RecommendedViewModel,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f))

        Box(modifier = Modifier
            .clip(primaryClip())
            .fillMaxWidth()
            .weight(7f)
            .fillMaxHeight()
            .background(
                colorContainerBg
            ))
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier.fillMaxWidth(.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Container(weight = 1f) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Container(weight = .7f) {

                        Image(
                            painter = painterResource(id = R.drawable.avatar_test),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(primaryClip())
                                .border(
                                    width = 1.dp,
                                    color = animatedColorPrimary(),
                                    shape = primaryClip()
                                )
                                .size(100.dp),
                            contentScale = ContentScale.Crop
                        )

                    }

                    Container(weight = 1.4f) {

                        Column(
                            modifier = Modifier.fillMaxSize(.9f),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            AppText(
                                text = "SuperTusa2008",
                                weight = TextWeight.BOLD,
                                size = TextSize.TITLE_MEDIUM
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.geo_tag),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    contentScale = ContentScale.FillWidth
                                )

                                AppText(
                                    text = "Нижний Новгород",
                                    weight = TextWeight.REGULAR,
                                    size = TextSize.BODY_LARGE
                                )
                            }
                        }

                    }
                }
            }


            Container(weight = 1.8f) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AppText(text = stringResource(id = R.string.about), weight = TextWeight.MEDIUM, size = TextSize.BODY_LARGE)

                    AppText(
                        text = stringResource(id = R.string.about_test_user),
                        weight = TextWeight.REGULAR,
                        size = TextSize.BODY_LARGE
                    )
                }
            }

            Container(weight = 1.4f) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    AddPersonalData(img = com.where.to.go.component.R.drawable.telegram, value = "test_test") {

                    }

                    AddPersonalData(img = com.where.to.go.component.R.drawable.vk, value = "") {

                    }

                    AddPersonalData(img = com.where.to.go.component.R.drawable.phone, value = "") {

                    }
                }
            }

            Container(weight = 1f) {

            }

        }
    }



}