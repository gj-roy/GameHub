package ca.on.hojat.gamenews.feature_category.di

import dagger.MapKey

@MapKey
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
internal annotation class CategoryKey(val type: Type) {

    enum class Type {
        POPULAR,
        RECENTLY_RELEASED,
        COMING_SOON,
        MOST_ANTICIPATED
    }
}
