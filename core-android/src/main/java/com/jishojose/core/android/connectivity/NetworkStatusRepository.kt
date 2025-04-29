package com.jishojose.core.android.connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.RemoteException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

interface NetworkStatusRepository {
    val state: StateFlow<NetworkStatus>
}

class NetworkStatusRepositoryImpl(
    private val connectivityManager: ConnectivityManager,
    private val scope: CoroutineScope = MainScope(),
) : NetworkStatusRepository {
    private var callback: ConnectivityManager.NetworkCallback? = null

    private val _state = MutableStateFlow(getCurrentNetworkStatus())
    override val state = _state.asStateFlow()

    /**
     * Only subscribe to network callbacks if we have an active subscriber
     */
    init {
        _state
            .subscriptionCount
            .map { count -> count > 0 }
            .distinctUntilChanged()
            .onEach { active ->
                if (active) {
                    subscribeNetworkCallback()
                } else {
                    unsubscribeNetworkCallback()
                }
            }
            .launchIn(scope)
    }

    private fun getCurrentNetworkStatus(): NetworkStatus {
        return try {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?.let { connected ->
                    if (connected) {
                        NetworkStatus.NetworkStatusConnected
                    } else {
                        NetworkStatus.NetworkStatusDisconnected
                    }
                } ?: NetworkStatus.NetworkStatusUnknown
        } catch (e: RemoteException) {
            NetworkStatus.NetworkStatusUnknown
        }
    }

    private fun subscribeNetworkCallback() {
        if (callback != null) return
        callback = NetworkCallbackImpl().also { connectivityManager.registerDefaultNetworkCallback(it) }
        emitNetworkState(getCurrentNetworkStatus())
    }

    private fun unsubscribeNetworkCallback() {
        callback?.run { connectivityManager.unregisterNetworkCallback(this) }
        callback = null
    }

    private fun emitNetworkState(newState: NetworkStatus) {
        scope.launch {
            _state.emit(newState)
        }
    }

    private inner class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) = emitNetworkState(NetworkStatus.NetworkStatusConnected)
        override fun onLost(network: Network) = emitNetworkState(NetworkStatus.NetworkStatusDisconnected)
    }
}
