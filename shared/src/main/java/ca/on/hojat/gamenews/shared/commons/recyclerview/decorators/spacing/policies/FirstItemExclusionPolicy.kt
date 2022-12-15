package ca.on.hojat.gamenews.shared.commons.recyclerview.decorators.spacing.policies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ca.on.hojat.gamenews.shared.commons.recyclerview.decorators.spacing.SpacingItemDecorator

class FirstItemExclusionPolicy : SpacingItemDecorator.ItemExclusionPolicy {

    override fun shouldExclude(view: View, parent: RecyclerView): Boolean {
        return (parent.getChildAdapterPosition(view) == 0)
    }
}
