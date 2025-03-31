package com.where.to.go.main.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.where.to.go.internet.models.UserRole
import com.where.to.go.main.vms.NavigationViewModel

class NavigationViewModelFactory(private val role: UserRole) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NavigationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NavigationViewModel(role) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}