package org.example.blueegg

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class AndroidSoundPlayer(context: Context) : SoundPlayer {
    private val appContext = context.applicationContext

    override fun start() {
        val intent = Intent(appContext, SoundForegroundService::class.java).apply {
            action = SoundForegroundService.ACTION_START
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            appContext.startForegroundService(intent)
        } else {
            appContext.startService(intent)
        }
    }

    override fun stop() {
        val intent = Intent(appContext, SoundForegroundService::class.java).apply {
            action = SoundForegroundService.ACTION_STOP
        }
        appContext.startService(intent)
    }

    override fun release() {
        // No-op. Service lifecycle owns background playback resources.
    }
}

@Composable
actual fun rememberSoundPlayer(): SoundPlayer {
    val context = LocalContext.current
    return remember(context) {
        AndroidSoundPlayer(context)
    }
}
