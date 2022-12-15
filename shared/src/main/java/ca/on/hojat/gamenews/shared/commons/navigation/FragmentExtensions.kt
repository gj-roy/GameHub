@file:JvmName("FragmentUtils")

package ca.on.hojat.gamenews.shared.commons.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

val Fragment.navController: NavController
    get() = findNavController()
