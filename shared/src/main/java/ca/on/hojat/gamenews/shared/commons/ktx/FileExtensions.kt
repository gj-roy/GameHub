@file:JvmName("FileUtils")

package ca.on.hojat.gamenews.shared.commons.ktx

import android.os.FileObserver
import ca.on.hojat.gamenews.shared.commons.core.SdkInfo
import java.io.File

@Suppress("DEPRECATION")
inline fun File.newFileObserver(
    events: Int = FileObserver.ALL_EVENTS,
    crossinline onEventListener: (Int, String?) -> Unit
): FileObserver {
    return if (SdkInfo.IS_AT_LEAST_10) {
        object : FileObserver(this, events) {
            override fun onEvent(event: Int, path: String?) = onEventListener(event, path)
        }
    } else {
        object : FileObserver(this.absolutePath, events) {
            override fun onEvent(event: Int, path: String?) = onEventListener(event, path)
        }
    }
}

fun File.fileList(): List<File> {
    if (!isDirectory) return emptyList()

    return (listFiles()?.toList() ?: emptyList())
}
