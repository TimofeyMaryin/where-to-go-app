package com.where.to.go.main

import android.annotation.SuppressLint
import android.provider.Telephony.Mms.Part
import android.util.Log
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.CategoryToggle
import com.where.to.go.component.CustomSearchView
import com.where.to.go.component.GlobalContainer
import com.where.to.go.component.LargePartyView
import com.where.to.go.component.SearchTextField
import com.where.to.go.component.SquareButton
import com.where.to.go.component.TextFieldType
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.WhereToGoApplicationTheme
import com.where.to.go.component.colorBg


@SuppressLint("ResourceType")
fun ComponentActivity.recommendsFragment() {
    enableEdgeToEdge()
    var search by mutableStateOf("")
    val categories = mutableStateListOf(false, false, false, false)

    setContent {
        WhereToGoApplicationTheme {
            Scaffold { innerPadding ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {

                    GlobalContainer(
                        topBarStart = {
                            SquareButton(icon = com.where.to.go.component.R.drawable.ic_launcher_foreground) {
                                // TODO Show menu
                            }

                            AppText(text = "Рекомендации", weight = TextWeight.REGULAR, size = TextSize.TITLE_MEDIUM)

                            SquareButton(icon = com.where.to.go.component.R.drawable.ic_launcher_foreground) {
                                // TODO Settings
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.9f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                        ) {
                            CustomSearchView(
                                hint = "Поиск",
                                value = search,
                                onValueChange = { search = it }, // Обновляем состояние
                                onSearchClick = {
                                    // TODO Search
                                }
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, bottom = 20.dp)
                            ) {
                                items(categories.size) { index ->
                                    CategoryToggle(
                                        isChecked = categories[index],
                                        onToggle = { newState ->
                                            categories[index] = newState // Обновляем состояние
                                            Log.e("ONTOGGLE", categories[index].toString())
                                        },
                                        label = "Category ${index + 1}"
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween // Распределяет элементы по краям
                            ) {
                                AppText(
                                    text = "Рекомендованные",
                                    modifier = Modifier.padding(bottom = 20.dp).align(Alignment.CenterVertically),
                                    weight = TextWeight.REGULAR,
                                    size = TextSize.TITLE_MEDIUM,
                                )

                                Button(
                                    onClick = { /* Действие при нажатии на кнопку */ },
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                ) {
                                    Text("Кнопка") // Текст на кнопке
                                }
                            }

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                items(categories.size) { index ->
                                    LargePartyView(
                                        title = "Test party",
                                        date = "Tommorow",
                                        guestCount = 25,
                                        price = "1/2",
                                        backgroundImageResId = com.where.to.go.component.R.drawable.test
                                    ) {

                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
    }
}