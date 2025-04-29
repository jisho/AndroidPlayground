package com.jishojose.core.android.connectivity

sealed class NetworkStatus {

    data object NetworkStatusConnected : NetworkStatus()

    data object NetworkStatusDisconnected : NetworkStatus()

    data object NetworkStatusUnknown : NetworkStatus()
}
fun NetworkStatus.isNotConnected(): Boolean =
    (this == NetworkStatus.NetworkStatusDisconnected) || (this == NetworkStatus.NetworkStatusUnknown)
