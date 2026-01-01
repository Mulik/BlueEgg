package org.example.blueegg

import androidx.compose.runtime.Composable

interface SoundPlayer {
    fun play()
    fun release()
}

@Composable
expect fun rememberSoundPlayer(): SoundPlayer
