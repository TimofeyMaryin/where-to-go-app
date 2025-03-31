package com.where.to.go.main.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.where.to.go.component.AppText
import com.where.to.go.component.ButtonColor
import com.where.to.go.component.PrimaryButton
import com.where.to.go.component.SquareButton
import com.where.to.go.component.WebImage
import com.where.to.go.component.secondaryClip
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.animateBrushPrimary
import com.where.to.go.component.values.blue
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.offset
import com.where.to.go.internet.models.Party
import com.where.to.go.main.R
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
            .align(Alignment.TopCenter)
            .padding(horizontal = offset), verticalArrangement = Arrangement.Top) {
            Box(modifier = Modifier
                .height(216.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))){
                WebImage(url = party.image,
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = RoundedCornerShape(25.dp))
                        .fillMaxSize())
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()) {
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()){
                    AppText(text = party.name, weight = TextWeight.BOLD, size = TextSize.TITLE,
                        modifier = Modifier.align(Alignment.TopStart))
                    AppText(text = "Смотрящий хаты", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                        modifier = Modifier.align(Alignment.BottomStart))
                }
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()){
                    Row(modifier = Modifier.align(Alignment.TopEnd),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)){
                        AppText(text = "3.7", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                            modifier = Modifier)
                        RatingBar(
                            value = 3.7f,
                            size = 17.dp,
                            spaceBetween = 1.dp,
                            style = RatingBarStyle.Fill(),
                            isIndicator = true,
                            onValueChange = {
                            },
                            onRatingChanged = {

                            },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    AppText(text = "500 оценок", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                        modifier = Modifier.align(Alignment.BottomEnd))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(

                    painter = painterResource(id = R.drawable.category),
                    contentDescription = null,
                    tint = blue,
                    modifier = Modifier.size(18.dp)
                )
                AppText(text = "${stringResource(id = R.string.category)}:", weight = TextWeight.BOLD, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
                AppText(text = "Category", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(

                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null,
                    tint = blue,
                    modifier = Modifier.size(18.dp)
                )
                AppText(text = "${stringResource(id = R.string.location)}:", weight = TextWeight.BOLD, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
                AppText(text = "location", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(

                    painter = painterResource(id = R.drawable.date),
                    contentDescription = null,
                    tint = blue,
                    modifier = Modifier.size(18.dp)
                )
                AppText(text = "${stringResource(id = R.string.date)}:", weight = TextWeight.BOLD, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
                AppText(text = "${party.date}", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp))    {
                Icon(

                    painter = painterResource(id = R.drawable.money),
                    contentDescription = null,
                    tint = blue,
                    modifier = Modifier.size(18.dp)
                )
                AppText(text = "${stringResource(id = R.string.enter_price)}:", weight = TextWeight.BOLD, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
                AppText(text = "${party.price}", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE,
                    modifier = Modifier)
            }
            Spacer(modifier = Modifier.height(20.dp))

            AppText(text = "${stringResource(id = R.string.description)}:", weight = TextWeight.BOLD, size = TextSize.BODY_LARGE,
                modifier = Modifier)
            Spacer(modifier = Modifier.height(15.dp))

            AppText(text = "${party.about}", weight = TextWeight.REGULAR, size = TextSize.BODY_LARGE, lineHeight = TextSize.BODY_LARGE.size.value.toInt(),
                modifier = Modifier)
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(horizontal = offset)){
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = offset).height(70.dp).align(
            Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween){
            PrimaryButton(value = "Начать", color = ButtonColor.COLORFUL, modifier = Modifier.fillMaxHeight(),
                aspectRatio = 3f) {

            }
            Box(
                modifier = Modifier
                    .clip(secondaryClip())
                    .fillMaxHeight()
                    .background(colorContainerBg)
                    .aspectRatio(1f)
                    .clickable {  },
                contentAlignment = Alignment.Center,
            ) {
                Icon(

                    painter = painterResource(id = R.drawable.hamburger_menu_button_img_1),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp).align(Alignment.Center)
                )
            }
        }

    }

}