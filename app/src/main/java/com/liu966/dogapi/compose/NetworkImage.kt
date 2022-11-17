package com.liu966.dogapi.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun NetworkImage(url: String?, modifier: Modifier) {
    return AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
    )
}