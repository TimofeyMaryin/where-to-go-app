package com.where.to.go.main

import android.os.Bundle
import androidx.core.app.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFragment()
    }
}