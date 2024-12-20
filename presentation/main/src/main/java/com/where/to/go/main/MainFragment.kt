package com.where.to.go.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.where.to.go.component.WhereToGoApplicationTheme
import com.where.to.go.main.navigation.AppNavigation
import com.where.to.go.main.vms.ImageEditorViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel


fun ComponentActivity.mainFragment(
    recommendsViewModel: RecommendedViewModel,
    profileViewModel: ProfileViewModel,
    editorViewModel: ImageEditorViewModel
) {


    enableEdgeToEdge()
    setContent {
        WhereToGoApplicationTheme {
            Scaffold {
                AppNavigation(recommendsViewModel, profileViewModel, editorViewModel)
                it
            }
        }
    }
}