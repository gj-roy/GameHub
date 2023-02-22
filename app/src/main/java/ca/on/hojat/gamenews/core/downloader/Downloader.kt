package ca.on.hojat.gamenews.core.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.core.net.toUri

/**
 * The API part that performs the download (so we don't have to do it in our ViewModels).
 */
interface Downloader {
    fun downloadFile(url: String): Long
}


class DownloaderImpl(
    context: Context,
) : Downloader {


    private val downloadManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(DownloadManager::class.java)
    } else {
        TODO("need to do something for API 21 and 22 because this feature isn't available there")
    }

    override fun downloadFile(url: String): Long {
        val fileName = url.substring(url.lastIndexOf('/') + 1)

        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("The name of the game goes here")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )

        return downloadManager.enqueue(request)
    }

}