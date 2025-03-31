package com.where.to.go.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.colorContainerBg
import com.where.to.go.component.values.TextFieldType
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.colorBg
import com.where.to.go.component.values.offset

@Composable
fun PartyFilters(
    modifier: Modifier = Modifier,
    onApply: (Pair<String, String>, Pair<String, String>, Pair<String, String>) -> Unit
)  {
    var date by remember { mutableStateOf(Pair("", "")) }
    var price by remember { mutableStateOf(Pair("", "")) }
    var guestsCount by remember { mutableStateOf(Pair("", "")) }

    Box(modifier = modifier
        .fillMaxSize()
        .background(
            color = colorContainerBg,
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
        )){

        Box(modifier = modifier
            .padding(offset)
            .fillMaxSize()){

            AppText(
                text = stringResource(id = R.string.filter),
                size = TextSize.TITLE,
                weight = TextWeight.MEDIUM)
            Column (modifier = modifier
                .padding(top = 36.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ){
                AppRangeTextField(
                    hint = stringResource(id = R.string.price),
                    onRangeChange = {f, s -> price = Pair(f, s)},
                    range = price,
                    type = TextFieldType.NUMBER,
                    modifier = Modifier
                )
                AppRangeTextField(
                    hint = stringResource(id = R.string.date),
                    onRangeChange = {f, s -> date = Pair(f, s)},
                    range = date,
                    type = TextFieldType.DATE,
                    modifier = Modifier
                )
                AppRangeTextField(
                    hint = stringResource(id = R.string.guests_count),
                    onRangeChange = {f, s -> guestsCount = Pair(f, s)},
                    range = guestsCount,
                    type = TextFieldType.NUMBER,
                    modifier = Modifier
                )

            }
            PrimaryButton(
                value = stringResource(id = R.string.apply),
                color = ButtonColor.COLORFUL,
                modifier = Modifier.align(Alignment.BottomStart)) {

                onApply(date,

                    Pair(if(price.first == ""){"0"}else{price.first },
                        if(price.second== ""){"${Int.MAX_VALUE}"}else{price.second}),

                    Pair(if(guestsCount.first == ""){"0"}else{guestsCount.first},
                        if(guestsCount.second == ""){"${Int.MAX_VALUE}"}else{guestsCount.second}))

            }
        }
        Box(modifier = Modifier
            .width(40.dp)
            .padding(top = 10.dp)
            .height(5.dp)
            .background(colorBg, shape = RoundedCornerShape(3.dp))
            .align(Alignment.TopCenter))
    }
}
@Preview
@Composable
fun PartyFiltersPreview(
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PartyFilters { date, price, guestsCount ->

        }
    }
}