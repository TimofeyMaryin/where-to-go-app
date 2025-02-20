package com.where.to.go.main.fragment

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.where.to.go.component.AppText
import com.where.to.go.component.Container
import com.where.to.go.component.values.TextSize
import com.where.to.go.component.values.TextWeight
import com.where.to.go.component.values.animateIconColor
import com.where.to.go.component.values.brushPrimary
import com.where.to.go.main.vms.NavigationViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SchedulePartyFragment(
    navigateViewModel: NavigationViewModel
) {
    val allMonth = remember { getCurrentAndNextFiveMonths().toMutableList() }

    val pagerState = rememberPagerState { allMonth.size }
    val scope = rememberCoroutineScope()

    BackHandler {

    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        
        Container(weight = 1f) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Container(weight = 1f, alignment = Alignment.CenterEnd) {
                    CalendarButton(ic = Icons.Default.KeyboardArrowLeft, enable = true) {

                    }
                }
                Container(weight = 1.5f) {
                    HorizontalPager(
                        state = pagerState,
                        userScrollEnabled = false,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AppText(
                                text = allMonth[pagerState.currentPage],
                                weight = TextWeight.REGULAR,
                                size = TextSize.TITLE,
                                textAlign = TextAlign.Center
                            )

                        }
                    }

                }

                Container(weight = 1f, alignment = Alignment.CenterStart) {
                    CalendarButton(ic = Icons.Default.KeyboardArrowRight, enable = true) {

                    }
                }


            }

        }
        
        Container(weight = 9f) {

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = true
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(50) {
                        Box(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .fillParentMaxWidth(.9f)
                                .height(50.dp)
                                .background(brushPrimary)
                        )
                    }
                }
            }


        }
        
    }
}


@Composable
private fun CalendarButton(
    ic: ImageVector,
    enable: Boolean,
    onClick: () -> Unit
) {
    
    IconButton(
        onClick = { if (enable) onClick() }
    ) {
        Icon(
            imageVector = ic,
            contentDescription = null,
            modifier = Modifier.size(35.dp),
            tint = animateIconColor()
        )    
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentAndNextFiveMonths(): List<String> {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy") // Формат для отображения месяца и года
    return (0..5).map { currentDate.plusMonths(it.toLong()).format(formatter) }
}
