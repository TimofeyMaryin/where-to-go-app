package com.where.to.go.main

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import com.where.to.go.component.values.WhereToGoApplicationTheme
import com.where.to.go.main.navigation.AppNavigation
import com.where.to.go.main.vms.EditProfileViewModel
import com.where.to.go.main.vms.PartyViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel


@RequiresApi(Build.VERSION_CODES.O)
fun ComponentActivity.mainFragment(
    recommendsViewModel: RecommendedViewModel,
    partyViewModel: PartyViewModel,
    profileViewModel: ProfileViewModel,
    editorViewModel: EditProfileViewModel
) {


    enableEdgeToEdge()
    setContent {
        WhereToGoApplicationTheme {
            Scaffold {
                AppNavigation(
                    recommendsViewModel = recommendsViewModel,
                    profileViewModel = profileViewModel,
                    editorViewModel = editorViewModel,
                    partyViewModel = partyViewModel)
                it
            }
        }
    }
}