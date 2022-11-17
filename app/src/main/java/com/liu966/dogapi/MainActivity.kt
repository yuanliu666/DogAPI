package com.liu966.dogapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.liu966.dogapi.compose.NetworkImage
import com.liu966.dogapi.data.APIClient
import com.liu966.dogapi.data.DogAPI
import com.liu966.dogapi.ui.theme.DogAPITheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val dogApi = APIClient.getClient().create(DogAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    val url = remember { mutableStateOf("") }
                    LaunchedEffect(key1 = Unit) {
                        coroutineScope.launch {
                            url.value = dogApi.getRandomSingleDogImage().body()?.message ?: ""
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                        NetworkImage(url = url.value, modifier = Modifier.fillMaxSize())
                        Button(onClick = {
                            coroutineScope.launch {
                                url.value = dogApi.getRandomSingleDogImage().body()?.message ?: ""
                            }
                        },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                        ) {
                            Text(text = "Refresh", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}