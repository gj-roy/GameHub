package ca.on.hojat.gamenews.shared.extensions

import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.google.android.material.card.MaterialCardView


fun MaterialCardView.setRippleColor(@ColorInt color: Int) {
    rippleColor = color.toColorStateList()
}


var MaterialCardView.contentLeftPadding: Int
    set(value) {
        updateContentPadding(leftPadding = value)
    }
    get() = contentPaddingLeft

var MaterialCardView.contentTopPadding: Int
    set(value) {
        updateContentPadding(topPadding = value)
    }
    get() = contentPaddingTop

var MaterialCardView.contentRightPadding: Int
    set(value) {
        updateContentPadding(rightPadding = value)
    }
    get() = contentPaddingRight

var MaterialCardView.contentBottomPadding: Int
    set(value) {
        updateContentPadding(bottomPadding = value)
    }
    get() = contentPaddingBottom

fun MaterialCardView.updateContentPadding(
    @Px leftPadding: Int = this.contentPaddingLeft,
    @Px topPadding: Int = this.contentPaddingTop,
    @Px rightPadding: Int = this.contentPaddingRight,
    @Px bottomPadding: Int = this.contentPaddingBottom
) {
    setContentPadding(leftPadding, topPadding, rightPadding, bottomPadding)
}
