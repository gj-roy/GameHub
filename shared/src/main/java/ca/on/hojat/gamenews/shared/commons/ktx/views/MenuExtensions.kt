@file:JvmName("MenuUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem

fun Menu.addItem(
    id: Int,
    title: CharSequence,
    icon: Drawable,
    groupId: Int = Menu.NONE,
    order: Int = Menu.NONE
): MenuItem {
    return add(groupId, id, order, title)
        .apply { this.icon = icon }
}
