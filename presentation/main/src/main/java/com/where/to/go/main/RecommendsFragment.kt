package com.where.to.go.main

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.where.to.go.component.AppText
import com.where.to.go.component.AppTextField
import com.where.to.go.component.CategoryToggle
import com.where.to.go.component.CustomSearchView
import com.where.to.go.component.GlobalContainer
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
    var search = ""

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
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CustomSearchView("поиск", value = search, {
                                search = it
                            }, {
                                // TODO Search
                            })
                            Row {
                                CategoryToggle(isChecked = false, onToggle ={ } , label = "Category 1")
                                CategoryToggle(isChecked = false, onToggle ={} , label = "Category 2")
                                CategoryToggle(isChecked = false, onToggle ={} , label = "Category 3")
                            }
                        }

                    }
                }
            }
        }
    }
}