package com.example.ebaydemo.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.ebaydemo.util.log.Logger
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import javax.inject.Singleton

@Singleton
class NetworkHelper (private val context: Context) {

    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            var networkInfo = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            if (!networkInfo)
                networkInfo = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            return networkInfo
        } else {
            var networkInfo =
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) ?: return false
            if (!networkInfo.isConnected) {
                networkInfo =
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        ?: return false
            }
            return networkInfo.isConnected
        }
    }
}