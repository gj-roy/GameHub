package ca.on.hojat.gamenews.shared.extensions

import com.google.android.material.tabs.TabLayout

inline fun TabLayout.addOnTabSelectedListener(
    crossinline onTabSelected: (TabLayout.Tab) -> Unit = {},
    crossinline onTabReselected: (TabLayout.Tab) -> Unit = {},
    crossinline onTabUnselected: (TabLayout.Tab) -> Unit = {}
): TabLayout.OnTabSelectedListener {
    return object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) = onTabSelected(tab)
        override fun onTabReselected(tab: TabLayout.Tab) = onTabReselected(tab)
        override fun onTabUnselected(tab: TabLayout.Tab) = onTabUnselected(tab)
    }
        .also(::addOnTabSelectedListener)
}
