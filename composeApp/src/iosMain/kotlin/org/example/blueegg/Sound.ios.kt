package org.example.blueegg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.AudioToolbox.AudioServicesPlaySystemSound
import platform.AudioToolbox.kSystemSoundID_Vibrate

class IOSSoundPlayer : SoundPlayer {
    // A simple system sound often used for keyboard clicks
    private val soundId = 1104u

    override fun play() {
        AudioServicesPlaySystemSound(soundId)
    }

    override fun release() {
        // No specific release/dispose needed for this simple system sound
    }
}

@Composable
actual fun rememberSoundPlayer(): SoundPlayer {
    return remember { IOSSoundPlayer() }
}
