package org.example.blueegg

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val soundPlayer = rememberSoundPlayer()

    DisposableEffect(soundPlayer) {
        onDispose {
            soundPlayer.release()
        }
    }

    MaterialTheme {
        var isSoundOn by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                isSoundOn = !isSoundOn
                if (isSoundOn) {
                    soundPlayer.start()
                } else {
                    soundPlayer.stop()
                }
            }) {
                Text(if (isSoundOn) "Sound: ON" else "Sound: OFF")
            }
        }
    }
}
