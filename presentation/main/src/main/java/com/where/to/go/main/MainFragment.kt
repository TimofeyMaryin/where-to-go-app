package com.where.to.go.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.where.to.go.component.AppText
import com.where.to.go.component.TextSize
import com.where.to.go.component.TextWeight
import com.where.to.go.component.WhereToGoApplicationTheme
import com.where.to.go.main.navigation.MainScreenNavigation


fun ComponentActivity.mainFragment() {
    enableEdgeToEdge()
    setContent {
        WhereToGoApplicationTheme {
            Scaffold {
                MainScreenNavigation()
                it
            }
        }
    }
}