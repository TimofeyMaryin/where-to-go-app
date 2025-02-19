package com.where.to.go.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.RequestState
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.main.vms.EditProfileViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel by viewModels<RecommendedViewModel>()
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val editorViewModel by viewModels<EditProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mainFragment(viewModel, profileViewModel, editorViewModel)
        profileViewModel.findUser()

        profileViewModel.findUserState.observe(this) { state ->
            when {
                state.isLoading -> {
                }
                state.data != null -> {
                    Toast.makeText(this, "User okay ${profileViewModel.loginUser == null}", Toast.LENGTH_LONG).show()
                    editorViewModel.loginUser = profileViewModel.loginUser
                }
                state.error != null -> {
                    Toast.makeText(this, "User not okay ${state.error}", Toast.LENGTH_LONG).show()
                    Log.e("USER", state.error.toString())
                }
            }
        }
    }

    init {
    }
}