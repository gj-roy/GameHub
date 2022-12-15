package ca.on.hojat.gamenews.shared.commons.network.utils

import android.net.ConnectivityManager
import android.net.Network
import com.paulrybitskyi.commons.network.model.NetworkType
import java.util.concurrent.ConcurrentHashMap

class NetworkCallback(
    private val networkTypeProvider: NetworkTypeProvider,
    private val listener: NetworkListener
) : ConnectivityManager.NetworkCallback() {

    private val Network.id: String
        get() = toString()

    private val activeNetworksMap = ConcurrentHashMap<String, NetworkType>()

    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        networkTypeProvider.getNetworkType(network)
            .also { activeNetworksMap[network.id] = it }
            .also { listener.onNetworkConnected(it) }
    }

    override fun onLost(network: Network) {
        super.onLost(network)

        (activeNetworksMap.remove(network.id) ?: NetworkType.UNDEFINED)
            .also { listener.onNetworkDisconnected(it) }
    }
}
