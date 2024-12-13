package com.where.to.go.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GlobalContainer(
    topBarStart: @Composable RowScope.() -> Unit,
    topBarEnd: (@Composable () -> Unit)? = null,
    isFullScreen: Boolean = false,
    content: @Composable () -> Unit
) {
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxSize(if (isFullScreen) 1f else .9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(if (isFullScreen) .9f else 1f)
                    .weight(
                        if (isFullScreen) 1.4f else 1f
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = if (!isFullScreen) Alignment.CenterVertically else Alignment.Bottom,
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
                    .weight(10f),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
        
    }
    
}