package org.example.blueegg

import android.content.Context
import android.media.RingtoneManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class AndroidSoundPlayer(context: Context) : SoundPlayer {
    private val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    private val ringtone = RingtoneManager.getRingtone(context, notificationSound)

    override fun play() {
        if (!ringtone.isPlaying) {
            ringtone.play()
        }
    }

    override fun release() {
        // Android's RingtoneManager handles its own resources, no-op needed.
    }
}

@Composable
actual fun rememberSoundPlayer(): SoundPlayer {
    val context = LocalContext.current
    return remember(context) {
        AndroidSoundPlayer(context)
    }
}
