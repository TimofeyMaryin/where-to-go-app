package com.where.to.go.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()

        setContent {  }
    }
}

// 127.0.0.1
@Composable
fun Greeting(data: String, testData: String, onClick: () -> Unit, onTestClick: () -> Unit,) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(text = "Давай получим данные")
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Данные: $data")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Тест: $testData")
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { onClick() }) {
            Text(text = "Получить всех пользователей")
        }

        Button(onClick = { onTestClick() }) {
            Text(text = "Тестовый клик на сервер")
        }

    }
}



