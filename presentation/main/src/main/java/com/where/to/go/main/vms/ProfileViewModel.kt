package com.where.to.go.main.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.where.to.go.internet.models.User

class ProfileViewModel: ViewModel() {
    var loginUser by mutableStateOf<User?>(null)

}