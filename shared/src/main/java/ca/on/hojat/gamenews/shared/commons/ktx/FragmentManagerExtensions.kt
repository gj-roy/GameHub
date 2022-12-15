
@file:JvmName("FragmentManagerUtils")

package ca.on.hojat.gamenews.shared.commons.ktx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

val FragmentManager.visibleFragment: Fragment?
    get() = fragments.firstOrNull { it.isVisible }
