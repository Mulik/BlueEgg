package org.example.blueegg

import androidx.compose.runtime.Composable

interface SoundPlayer {
    fun start()
    fun stop()
    fun release()
}

@Composable
expect fun rememberSoundPlayer(): SoundPlayer
