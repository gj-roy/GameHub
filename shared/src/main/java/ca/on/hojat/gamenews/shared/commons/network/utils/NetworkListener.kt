package ca.on.hojat.gamenews.shared.commons.network.utils

import ca.on.hojat.gamenews.shared.commons.network.model.NetworkType

interface NetworkListener {
    fun onNetworkConnected(networkType: NetworkType)
    fun onNetworkDisconnected(networkType: NetworkType)
}
