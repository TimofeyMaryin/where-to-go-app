package com.where.to.go.main.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.where.to.go.main.vms.RecommendedViewModel

@Composable
fun SchedulePartyFragment(
    navController: NavController,
    viewModel: RecommendedViewModel
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red))
}


@Preview
@Composable
private fun SchedulePartyFragmentPreview() {
    SchedulePartyFragment(
        navController = rememberNavController(),
        viewModel = RecommendedViewModel()
    )
}