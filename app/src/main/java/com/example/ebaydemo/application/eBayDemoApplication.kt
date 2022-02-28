package com.example.ebaydemo.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.bumptech.glide.manager.ConnectivityMonitor
import com.example.ebaydemo.util.common.LoadingDialog
import com.example.ebaydemo.util.network.InternetAvailabilityChecker
import com.example.ebaydemo.util.network.InternetConnectivityListener
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class eBayDemoApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks,
    InternetConnectivityListener {

    private var isConnected: Boolean = false
    private var mInternetAvailabilityChecker: InternetAvailabilityChecker? = null


    override fun onCreate() {
        super.onCreate()
        InternetAvailabilityChecker.init(this)
        /*Register Activity Lifecycle callbacks*/
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        mInternetAvailabilityChecker = InternetAvailabilityChecker.instance
        mInternetAvailabilityChecker?.addInternetConnectivityListener(this)
        LoadingDialog.bindWith(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        mInternetAvailabilityChecker?.removeInternetConnectivityChangeListener(this)
        LoadingDialog.unbind()
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        this.isConnected = isConnected
    }
}