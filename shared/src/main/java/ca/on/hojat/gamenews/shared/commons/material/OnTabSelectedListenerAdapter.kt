package ca.on.hojat.gamenews.shared.commons.material

import com.google.android.material.tabs.TabLayout

interface OnTabSelectedListenerAdapter : TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab) {}
    override fun onTabReselected(tab: TabLayout.Tab) {}
    override fun onTabUnselected(tab: TabLayout.Tab) {}
}
