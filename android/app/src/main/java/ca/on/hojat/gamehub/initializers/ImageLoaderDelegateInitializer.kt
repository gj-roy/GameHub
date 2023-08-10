package ca.on.hojat.gamehub.initializers

import ca.on.hojat.gamehub.presentation.images.ImageLoaderInitializer
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

@BindType(contributesTo = BindType.Collection.SET)
internal class ImageLoaderDelegateInitializer @Inject constructor(
    private val imageLoaderInitializer: ImageLoaderInitializer,
) : Initializer {

    override fun init() {
        imageLoaderInitializer.init()
    }
}
