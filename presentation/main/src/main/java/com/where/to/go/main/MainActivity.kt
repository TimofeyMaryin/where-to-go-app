package com.where.to.go.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.where.to.go.main.vms.RecommendedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel by viewModels<RecommendedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainFragment(viewModel)
    }
}