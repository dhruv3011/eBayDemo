package com.example.ebaydemo.di.modules

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.ebaydemo.BuildConfig
import com.example.ebaydemo.api.network.NetworkService
import com.example.ebaydemo.api.network.Networking
import com.example.ebaydemo.util.network.NetworkHelper
import com.example.ebaydemo.util.rx.RxSchedulerProvider
import com.example.ebaydemo.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton.
     */
    @Provides
    @Singleton
    fun provideNetworkService(@ApplicationContext context: Context): NetworkService =
        Networking.create(
            BuildConfig.BaseUrl,
            context
        )
    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper = NetworkHelper(context)
    @Provides
    fun provideAlertDialogBuilder(@ApplicationContext context: Context): AlertDialog.Builder = AlertDialog.Builder(context)
    @Provides
    fun provideLayoutInflater(@ApplicationContext context: Context): LayoutInflater = LayoutInflater.from(context)
}