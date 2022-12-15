package ca.on.hojat.gamenews.shared.commons.network.utils

import android.Manifest
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.paulrybitskyi.commons.network.model.NetworkType

interface NetworkTypeProvider {
    fun getActiveNetworkType(): NetworkType
    fun getNetworkType(network: Network): NetworkType
}

internal class NewNetworkTypeProvider(
    private val connectivityManager: ConnectivityManager
) : NetworkTypeProvider {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getActiveNetworkType(): NetworkType {
        return connectivityManager.activeNetwork
            ?.run { toNetworkType() }
            ?: NetworkType.UNDEFINED
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getNetworkType(network: Network): NetworkType {
        return network.toNetworkType()
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun Network.toNetworkType(): NetworkType {
        val capabilities =
            (connectivityManager.getNetworkCapabilities(this) ?: return NetworkType.UNDEFINED)

        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> NetworkType.WIFI
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> NetworkType.ETHERNET

            else -> NetworkType.UNDEFINED
        }
    }
}

@Suppress("DEPRECATION")
internal class LegacyNetworkTypeProvider(
    private val connectivityManager: ConnectivityManager
) : NetworkTypeProvider {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getActiveNetworkType(): NetworkType {
        return connectivityManager.activeNetworkInfo
            ?.run { toNetworkType() }
            ?: NetworkType.UNDEFINED
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getNetworkType(network: Network): NetworkType {
        return connectivityManager.getNetworkInfo(network)
            ?.run { toNetworkType() }
            ?: NetworkType.UNDEFINED
    }

    private fun NetworkInfo.toNetworkType(): NetworkType {
        return when (type) {
            TYPE_WIFI -> NetworkType.WIFI
            TYPE_MOBILE -> NetworkType.CELLULAR
            TYPE_ETHERNET -> NetworkType.ETHERNET

            else -> NetworkType.UNDEFINED
        }
    }
}
