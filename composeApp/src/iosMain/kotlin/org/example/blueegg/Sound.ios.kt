package org.example.blueegg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import platform.AudioToolbox.AudioServicesPlaySystemSound

class IOSSoundPlayer : SoundPlayer {
    // A simple system sound often used for keyboard clicks
    private val soundId = 1104u
    private val scope = CoroutineScope(Dispatchers.Default)
    private var playJob: Job? = null

    override fun start() {
        if (playJob?.isActive == true) return

        playJob = scope.launch {
            while (isActive) {
                AudioServicesPlaySystemSound(soundId)
                delay(1_000)
            }
        }
    }

    override fun stop() {
        playJob?.cancel()
        playJob = null
    }

    override fun release() {
        stop()
        scope.cancel()
    }
}

@Composable
actual fun rememberSoundPlayer(): SoundPlayer {
    return remember { IOSSoundPlayer() }
}
