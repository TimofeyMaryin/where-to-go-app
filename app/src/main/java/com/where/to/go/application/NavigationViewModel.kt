package com.where.to.go.application

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun createNavController(context: Context): NavHostController {
        return NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
    }
}