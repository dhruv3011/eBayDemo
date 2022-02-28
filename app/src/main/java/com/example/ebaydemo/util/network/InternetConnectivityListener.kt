package com.example.ebaydemo.util.network

interface InternetConnectivityListener {
    fun onInternetConnectivityChanged(isConnected: Boolean)
}