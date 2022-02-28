package com.example.ebaydemo.view.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.ebaydemo.api.dataClass.EarthquakeDataClass
import com.example.ebaydemo.mvvmBase.BaseViewModel
import com.example.ebaydemo.util.common.Resource
import com.example.ebaydemo.util.network.NetworkHelper
import com.example.ebaydemo.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val mainActivityRepository: MainActivityRepository,
    private val state: SavedStateHandle
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }

    fun saveState() {
        state.set("earthquakeDataLiveData", earthquakeDataLiveData.value)
    }

    val earthquakeDataLiveData : MutableLiveData<EarthquakeDataClass> = state.getLiveData("earthquakeDataLiveData", EarthquakeDataClass())

    /**
     * Initiating api call to get Earthquake Data
     */
    fun getEarthquakeData() {
        if (earthquakeDataLiveData.value?.earthquakes == null) {
            if (checkInternetConnectionWithMessage()) {
                compositeDisposable.addAll(
                    mainActivityRepository.getEarthquakeDataFromAPI()
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                                showDialog.postValue(false)
                                earthquakeDataLiveData.postValue(it)
                            }, {
                                showDialog.postValue(false)
                                messageString.postValue(Resource.error("Oops!! Something went wrong"))
                            }
                        ))
            }
        } else {
            earthquakeDataLiveData.value
        }
    }
}