package ca.on.hojat.gamenews.core.data.api.igdb.common.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER
)
annotation class Endpoint(val type: Type) {

    enum class Type {
        AUTH,
        GAMES
    }
}
