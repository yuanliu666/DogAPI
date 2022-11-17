package com.liu966.dogapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
                    RandomDogImage()
                }
            }
        }
    }

    @Composable
    fun RandomDogImage() {
        val coroutineScope = rememberCoroutineScope()
        val url = remember { mutableStateOf("") }
        LaunchedEffect(key1 = Unit) {
            coroutineScope.launch {
                url.value = dogApi.getRandomSingleDogImage().body()?.message ?: ""
            }
        }
        NetworkImage(url = url.value, modifier = Modifier.fillMaxSize())
    }
}