package com.where.to.go.main.vms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.where.to.go.internet.models.Party


class PartyViewModel: ViewModel() {
    var party: Party? = null

}