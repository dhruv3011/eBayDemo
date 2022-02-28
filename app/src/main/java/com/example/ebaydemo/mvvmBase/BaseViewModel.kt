package com.example.ebaydemo.mvvmBase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ebaydemo.R
import com.example.ebaydemo.util.common.Resource
import com.example.ebaydemo.util.network.NetworkHelper
import com.example.ebaydemo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()
    val showDialog: MutableLiveData<Boolean> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(isShowDialog: Boolean = true): Boolean =
        if (networkHelper.isNetworkConnected()) {
            showDialog.postValue(isShowDialog)
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            showDialog.postValue(false)
            false
        }

    abstract fun onCreate()
}