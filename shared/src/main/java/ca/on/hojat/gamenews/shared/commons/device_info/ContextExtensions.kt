@file:JvmName("ContextUtils")

package ca.on.hojat.gamenews.shared.commons.device_info

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.DisplayMetrics
import ca.on.hojat.gamenews.shared.commons.device_info.model.DeviceInfo
import ca.on.hojat.gamenews.shared.commons.device_info.model.ProductInfo
import ca.on.hojat.gamenews.shared.commons.device_info.model.screen.ScreenDensity.Companion.asScreenDensity
import ca.on.hojat.gamenews.shared.commons.device_info.model.screen.ScreenDimension
import ca.on.hojat.gamenews.shared.commons.device_info.model.screen.ScreenMetrics
import ca.on.hojat.gamenews.shared.commons.device_info.model.screen.ScreenScalingFactors
import ca.on.hojat.gamenews.shared.commons.device_info.model.screen.ScreenSizeCategory
import ca.on.hojat.gamenews.shared.commons.device_info.model.screen.ScreenSizeCategory.Companion.asScreenSizeCategory
import ca.on.hojat.gamenews.shared.extensions.configuration
import ca.on.hojat.gamenews.shared.extensions.displayMetrics
import ca.on.hojat.gamenews.shared.extensions.pxToDp

val Context.deviceInfo: DeviceInfo
    get() = DeviceInfo(
        productInfo = productInfo,
        screenMetrics = screenMetrics
    )

val Context.productInfo: ProductInfo
    get() = ProductInfo(
        modelName = Build.MODEL,
        productName = Build.PRODUCT,
        manufacturerName = Build.MANUFACTURER
    )

val Context.screenMetrics: ScreenMetrics
    get() = ScreenMetrics(
        width = displayMetrics.getScreenWidth(this),
        height = displayMetrics.getScreenHeight(this),
        sizeCategory = configuration.getScreenSizeCategory(),
        density = displayMetrics.densityDpi.asScreenDensity(),
        scalingFactors = displayMetrics.getScreenScalingFactors(),
        smallestWidthInDp = configuration.smallestScreenWidthDp
    )

private fun DisplayMetrics.getScreenWidth(context: Context): ScreenDimension {
    return ScreenDimension(
        sizeInPixels = widthPixels,
        sizeInDp = widthPixels.toFloat().pxToDp(context)
    )
}

private fun DisplayMetrics.getScreenHeight(context: Context): ScreenDimension {
    return ScreenDimension(
        sizeInPixels = heightPixels,
        sizeInDp = heightPixels.toFloat().pxToDp(context)
    )
}

private fun DisplayMetrics.getScreenScalingFactors(): ScreenScalingFactors {
    return ScreenScalingFactors(
        pixelScalingFactor = density,
        textPixelScalingFactor = scaledDensity
    )
}

private fun Configuration.getScreenSizeCategory(): ScreenSizeCategory {
    return (screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK).asScreenSizeCategory()
}
