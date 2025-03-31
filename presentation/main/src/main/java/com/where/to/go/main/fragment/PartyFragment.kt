package com.where.to.go.main.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.where.to.go.component.AppText
import com.where.to.go.component.WebImage
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.internet.models.Party
import com.where.to.go.main.vms.NavigationViewModel
import com.where.to.go.main.vms.PartyViewModel

@Composable
fun PartyFragment(
    viewModel: PartyViewModel,
    navigationViewModel: NavigationViewModel,
) {
    val party = viewModel.party!!
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter), verticalArrangement = Arrangement.Top) {
            Box(modifier = Modifier
                .height(216.dp)
                .fillMaxWidth()
                .background(shape = RoundedCornerShape(25.dp), color = Color.Transparent)){
                WebImage(url = party.image,
                    modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .height(50.dp).fillMaxWidth()) {
                Box(modifier = Modifier
                    .weight(1f)){
                    AppText(text = party.name, weight = TextWeight.BOLD, size = TextSize.TITLE,
                        modifier = Modifier.align(Alignment.TopStart))
                    AppText(text = "Смотрящий хаты", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                        modifier = Modifier.align(Alignment.BottomStart))
                }
                Box(modifier = Modifier
                    .weight(1f)){
                    AppText(text = party.name, weight = TextWeight.BOLD, size = TextSize.TITLE,
                        modifier = Modifier.align(Alignment.TopEnd))
                    AppText(text = "Смотрящий хаты", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                        modifier = Modifier.align(Alignment.BottomEnd))
                }
            }




        }
    }

}