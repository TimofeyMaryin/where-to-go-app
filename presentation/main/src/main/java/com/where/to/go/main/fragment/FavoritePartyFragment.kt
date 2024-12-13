package com.where.to.go.main.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.where.to.go.main.vms.RecommendedViewModel

@Composable
fun FavoritePartyFragment(
    navController: NavController,
    viewModel: RecommendedViewModel,
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Green))
}