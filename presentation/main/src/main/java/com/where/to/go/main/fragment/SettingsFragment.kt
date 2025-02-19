package com.where.to.go.main.fragment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.where.to.go.component.AppText
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.main.vms.NavigationViewModel

@Composable
fun SettingsFragment(
    navigationViewModel: NavigationViewModel,
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AppText(text = "Settings Fragment", weight = TextWeight.MEDIUM, size = TextSize.BODY_LARGE)
    }

}