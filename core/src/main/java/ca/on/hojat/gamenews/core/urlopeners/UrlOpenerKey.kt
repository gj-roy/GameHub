package ca.on.hojat.gamenews.core.urlopeners

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER
)
annotation class UrlOpenerKey(val type: Type) {

    enum class Type {
        NATIVE_APP,
        CUSTOM_TAB,
        BROWSER
    }
}
