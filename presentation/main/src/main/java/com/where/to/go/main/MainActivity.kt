package com.where.to.go.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.where.to.go.main.vms.EditProfileViewModel
import com.where.to.go.main.vms.PartyViewModel
import com.where.to.go.main.vms.ProfileViewModel
import com.where.to.go.main.vms.RecommendedViewModel
import com.where.to.go.main.vms.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val recommendsViewModel by viewModels<RecommendedViewModel>()
    private val scheduleViewModel by viewModels<ScheduleViewModel>()
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val editorViewModel by viewModels<EditProfileViewModel>()
    private val partyViewModel by viewModels<PartyViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mainFragment(
            recommendsViewModel = recommendsViewModel,
            scheduleViewModel = scheduleViewModel,
            profileViewModel = profileViewModel,
            editorViewModel = editorViewModel,
            partyViewModel = partyViewModel)
        profileViewModel.findUser()

        profileViewModel.findUserState.observe(this) { state ->
            when {
                state.isLoading -> {
                }
                state.data != null -> {
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