package com.seekho.anime.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.seekho.anime.R

@Composable
fun ExitConfirmationDialog(
    onExit: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.exit_app),
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = stringResource(R.string.are_you_sure_you_want_to_exit),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = onExit) {
                Text(stringResource(R.string.exit))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}