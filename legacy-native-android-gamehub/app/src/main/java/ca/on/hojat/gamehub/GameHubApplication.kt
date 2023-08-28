package ca.on.hojat.gamehub

import android.app.Application
import ca.on.hojat.gamehub.initializers.Initializer
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class GameHubApplication : Application() {

    @Inject
    lateinit var initializer: Initializer

    override fun onCreate() {
        super.onCreate()

        initializer.init()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
