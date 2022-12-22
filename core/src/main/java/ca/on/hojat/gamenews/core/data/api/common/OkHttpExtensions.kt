package ca.on.hojat.gamenews.core.data.api.common

import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun OkHttpClient.Builder.addInterceptorAsFirstInChain(
    interceptor: Interceptor
): OkHttpClient.Builder = apply {
    interceptors().add(0, interceptor)
}
