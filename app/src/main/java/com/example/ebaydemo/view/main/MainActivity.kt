package com.example.ebaydemo.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.ebaydemo.R
import com.example.ebaydemo.databinding.ActivityMainBinding
import com.example.ebaydemo.mvvmBase.BaseActivity
import com.example.ebaydemo.mvvmBase.BaseViewModel
import com.example.ebaydemo.view.map.MapsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    var dataBinding: ActivityMainBinding? = null
    private val mainActivityViewModel: MainActivityViewModel by viewModels ()
    val adapter = MainAdapter()

    override fun setDataBindingLayout() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding?.lifecycleOwner = this
    }

    override fun setupView(savedInstanceState: Bundle?) {
        dataBinding?.recyclerview?.adapter = adapter
        adapter.onItemClick = { earthquake ->
            // do something with your item
            val intent = Intent(this@MainActivity,MapsActivity::class.java);
            intent.putExtra("lat", earthquake?.lat)
            intent.putExtra("lng", earthquake?.lng)
            startActivity(intent);
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivityViewModel.getEarthquakeData()
    }

    override fun setUpViewModel(): BaseViewModel = mainActivityViewModel

    override fun setupObservers() {
        super.setupObservers()
        mainActivityViewModel.earthquakeDataLiveData.observe(this, Observer {
            adapter.setEarthquakeList(it.earthquakes) // inserting the data to local storage (Room DB) and setting values to live data
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mainActivityViewModel.saveState()
        super.onSaveInstanceState(outState)
    }
}