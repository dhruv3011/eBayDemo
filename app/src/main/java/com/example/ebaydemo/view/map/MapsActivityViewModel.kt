package com.example.ebaydemo.view.map


import com.example.ebaydemo.mvvmBase.BaseViewModel
import com.example.ebaydemo.util.network.NetworkHelper
import com.example.ebaydemo.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MapsActivityViewModel
@Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }

}