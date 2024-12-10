package com.where.to.go.application

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.where.to.go.application.ui.theme.WhereToGoApplicationTheme
import com.where.to.go.auth.AuthActivity
import com.where.to.go.internet.cases.UserUseCase
import com.where.to.go.internet.models.User

    class MainActivity : ComponentActivity() {
    private lateinit var userUseCase: UserUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userUseCase = UserUseCase()


        enableEdgeToEdge()

        setContent { TestScreen(userUseCase = userUseCase, this) }
    }
}


@Composable
fun TestScreen(
    userUseCase: UserUseCase,
    activity: MainActivity
) {
    val context = LocalContext.current
    var getUserState by remember { mutableIntStateOf(0) }
    var getTestState by remember {
        mutableStateOf(0)
    }

    var bodyTestCase by remember { mutableStateOf("") }

    var users = remember { mutableListOf<User>() }
    var isProgress by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = getUserState) {
        if (getUserState > 0) {
            isProgress = true
            users = userUseCase.getAllUsers().body()?.toMutableList() ?: emptyList<User>().toMutableList()
            isProgress = false

        }
    }

    LaunchedEffect(key1 = getTestState) {
        if (getTestState > 0) {
            bodyTestCase = userUseCase.testCall().body().toString()
        }
    }

    WhereToGoApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            if (isProgress) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {

                Greeting(
                    data = users.joinToString(" | "),
                    testData = bodyTestCase,
                    onTestClick = {
                        val intent = Intent(activity, AuthActivity::class.java)
                        activity.startActivity(intent)

                    },
                    onClick = { getUserState++ }
                )
            }
            innerPadding
        }

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



