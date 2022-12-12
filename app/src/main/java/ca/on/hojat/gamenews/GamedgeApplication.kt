package ca.on.hojat.gamenews

import android.app.Application
import ca.on.hojat.gamenews.initializers.Initializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
internal class GamedgeApplication : Application() {

    @Inject
    lateinit var initializer: Initializer

    override fun onCreate() {
        super.onCreate()

        initializer.init()
    }
}
