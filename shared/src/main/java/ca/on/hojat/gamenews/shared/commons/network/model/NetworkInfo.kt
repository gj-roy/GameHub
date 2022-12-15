package ca.on.hojat.gamenews.shared.commons.network.model

data class NetworkInfo(
    val isConnectedToNetwork: Boolean,
    val isNetworkConnectionMetered: Boolean,
    val activeNetworkType: NetworkType
)
