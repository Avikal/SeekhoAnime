package com.seekho.anime.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seekho.anime.R

@Composable
fun CommonErrorState(
    error: String?,
    onRetry: () -> Unit
) {
    val title =
        if (error == "NO_INTERNET") stringResource(R.string.no_internet_connection)
        else stringResource(R.string.something_went_wrong)

    val message =
        if (error != "NO_INTERNET") error?.removePrefix("ERROR:") else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = title, style = MaterialTheme.typography.titleLarge)

        message?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}