package ca.on.hojat.gamenews.core

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object SdkInfo {

    // API 33
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    @JvmField
    val IS_AT_LEAST_TIRAMISU = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)

    // API 30
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    @JvmField
    val IS_AT_LEAST_11 = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)

    // API 23
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
    @JvmField
    val IS_AT_LEAST_MARSHMALLOW = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
}
