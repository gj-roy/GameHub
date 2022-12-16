package ca.on.hojat.gamenews.shared.extensions

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import com.google.android.material.bottomnavigation.BottomNavigationView


fun BottomNavigationView.setItemColors(
    @ColorInt unselectedStateColor: Int,
    @ColorInt selectedStateColor: Int
) {
    ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf()
        ),
        intArrayOf(
            unselectedStateColor,
            selectedStateColor
        )
    ).also {
        itemTextColor = it
        itemIconTintList = it
    }
}
