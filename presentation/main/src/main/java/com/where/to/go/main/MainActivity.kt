package com.where.to.go.main

import android.os.Bundle
<<<<<<< HEAD
import androidx.activity.ComponentActivity
=======
import androidx.core.app.ComponentActivity
>>>>>>> origin/main
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
<<<<<<< HEAD
class MainActivity: ComponentActivity() {
=======
class MainActivity: androidx.activity.ComponentActivity() {
>>>>>>> origin/main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFragment()
    }
}