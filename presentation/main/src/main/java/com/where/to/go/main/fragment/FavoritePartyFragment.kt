package com.where.to.go.main.fragment

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.where.to.go.component.AppText
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.main.vms.RecommendedViewModel

@Composable
fun FavoritePartyFragment(
    viewModel: RecommendedViewModel,
) {

    BackHandler {

    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AppText(text = "А в дизе что тут?", weight = TextWeight.MEDIUM, size = TextSize.BODY_LARGE)
    }
}