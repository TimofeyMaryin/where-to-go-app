package com.where.to.go.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.where.to.go.component.values.offset

@Composable
fun GlobalContainer(
    horizontalOffset: Dp = offset,
    topBarStart: @Composable RowScope.() -> Unit,
    topBarEnd: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(primaryFillWidth),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        topBarStart()
                    }
                    if (topBarEnd != null) {
                        topBarEnd()
                    } else {
                        Spacer(modifier = Modifier.size(50.dp))
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(10f)
                    .padding(horizontal = horizontalOffset),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
        
    }
    
}