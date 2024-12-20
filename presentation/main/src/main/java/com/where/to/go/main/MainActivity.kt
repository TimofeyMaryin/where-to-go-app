package com.where.to.go.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.RestorePasswordModel
import com.where.to.go.internet.plugins.TokenManager
import com.where.to.go.internet.servers.UserServer
import com.where.to.go.main.vms.ImageEditorViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel by viewModels<RecommendedViewModel>()
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val editorViewModel by viewModels<ImageEditorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainFragment(viewModel, profileViewModel, editorViewModel)

    }

    init {
        UserServer.findUser(
            userUseCase = UserUseCase(),
            model = RestorePasswordModel(email = TokenManager.getEmail()),
            coroutineScope = lifecycleScope,
            onLoading = {
                Log.e("TAG", "MainActivity - onLoading: $it", )
            },
            onResult = {
                Log.e("TAG", "MainActivity - onResult: $it", )
                profileViewModel.loginUser = it
            },
            onError = {
                Log.e("TAG", "MainActivity - onError: $it", )
            }
        )
    }
}