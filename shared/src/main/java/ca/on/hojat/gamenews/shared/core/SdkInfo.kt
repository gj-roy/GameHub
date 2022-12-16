package ca.on.hojat.gamenews.shared.core

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object SdkInfo {

    @JvmField
    val SDK_VERSION = Build.VERSION.SDK_INT

    @JvmField
    val IS_AT_LEAST_OREO = (SDK_VERSION >= Build.VERSION_CODES.O)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    @JvmField
    val IS_AT_LEAST_11 = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
}
