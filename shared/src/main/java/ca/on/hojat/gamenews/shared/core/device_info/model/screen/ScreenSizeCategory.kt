package ca.on.hojat.gamenews.shared.core.device_info.model.screen

import android.content.res.Configuration

enum class ScreenSizeCategory(
    val layoutSize: Int,
    val title: String
) {

    UNDEFINED(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_UNDEFINED,
        title = "Undefined"
    ),
    SMALL(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_SMALL,
        title = "Small"
    ),
    NORMAL(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_NORMAL,
        title = "Normal"
    ),
    LARGE(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_LARGE,
        title = "Large"
    ),
    XLARGE(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_XLARGE,
        title = "XLarge"
    );

    companion object {

        @JvmName("forLayoutSize")
        @JvmStatic
        internal fun Int.asScreenSizeCategory(): ScreenSizeCategory {
            return values().find { it.layoutSize == this }
                ?: UNDEFINED
        }
    }
}
