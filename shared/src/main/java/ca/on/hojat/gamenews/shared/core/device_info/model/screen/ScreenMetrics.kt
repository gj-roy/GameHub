package ca.on.hojat.gamenews.shared.core.device_info.model.screen

data class ScreenMetrics(
    val width: ScreenDimension,
    val height: ScreenDimension,
    val sizeCategory: ScreenSizeCategory,
    val density: ScreenDensity,
    val scalingFactors: ScreenScalingFactors,
    val smallestWidthInDp: Int
)
