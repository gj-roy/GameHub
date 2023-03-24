@file:JvmName("PackageManagerUtils")
@file:Suppress("DEPRECATION")

package ca.on.hojat.gamenews.core.extensions

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import android.content.pm.PackageManager.ResolveInfoFlags
import android.content.pm.ResolveInfo
import android.net.Uri
import ca.on.hojat.gamenews.core.SdkInfo

/**
 * Tries to figure out a package name of a native application that
 * is able to open the given url.
 *
 * Note: Due to [Android 11 package visibility changes](https://g.co/dev/packagevisibility), this
 * method does not work on Android 11 and above.
 *
 * @param url The given url
 *
 * @return The package name of a native application or null.
 */
@SuppressLint("QueryPermissionsNeeded")
fun PackageManager.getNativeAppPackageForUrl(url: String): String? {
    val genericAppsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
    val resolvedGenericApps = queryIntentActivities(genericAppsIntent, 0).map {
        it.activityInfo.packageName
    }

    val specializedAppsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val resolvedSpecializedApps = queryIntentActivities(specializedAppsIntent, 0).map {
        it.activityInfo.packageName
    }

    return resolvedSpecializedApps.subtract(resolvedGenericApps).firstOrNull()
}

/**
 * Checks if the given url can be opened by a native application on the device.
 *
 * Note: Due to [Android 11 package visibility changes](https://g.co/dev/packagevisibility), this
 * method does not work on Android 11 and above.
 *
 * @param url The url to check
 *
 * @return true if the url can be opened by a native app; false otherwise
 */
fun PackageManager.canUrlBeOpenedByNativeApp(url: String): Boolean {
    return (getNativeAppPackageForUrl(url) != null)
}

/**
 * In older versions, it uses a deprecated method for providing [PackageInfo] but in
 * modern versions, that's been replaced with a safer option
 */
fun PackageManager.getPackageInfoSafely(context: Context): PackageInfo {
    return if (SdkInfo.IS_AT_LEAST_TIRAMISU) {
        // API 33+
        getPackageInfo(context.packageName, PackageInfoFlags.of(0))
    } else {
        // API 32 and below
        getPackageInfo(context.packageName, 0)
    }
}

/**
 * You give it an [Intent] and it will return a list of
 * all the app components that can resolve that intent.
 * In later APIs, it uses a deprecated function and in newer APIs,
 * calls a safer function.
 */
fun PackageManager.queryIntentActivitiesSafely(intent: Intent): List<ResolveInfo> {
    return if (SdkInfo.IS_AT_LEAST_TIRAMISU) {
        // API 33+
        queryIntentActivities(intent, ResolveInfoFlags.of(0))
    } else {
        // API 32 and below
        queryIntentActivities(intent, 0)
    }
}

/**
 * You give it an [Intent] and it will return the [ResolveInfo]
 * referring to the [Service] that can resolve this intent. In
 * later APIs, it uses a deprecated function and in newer APIs,
 * calls a safer function.
 */
fun PackageManager.resolveServiceSafely(serviceIntent: Intent): ResolveInfo? {
    return if (SdkInfo.IS_AT_LEAST_TIRAMISU) {
        // API 33+
        resolveService(serviceIntent, ResolveInfoFlags.of(0))
    } else {
        // API 32 and below
        resolveService(serviceIntent, 0)
    }
}