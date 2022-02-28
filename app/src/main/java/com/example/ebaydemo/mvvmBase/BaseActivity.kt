package com.example.ebaydemo.mvvmBase

import android.app.Activity
import android.app.job.JobScheduler
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.ebaydemo.util.common.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {
    lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = setUpViewModel()
        setDataBindingLayout()
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.showDialog.observe(this, Observer {
            showLoadingDialog(it)
        })
    }

    private fun showLoadingDialog(it: Boolean) {
        if (it) {
            LoadingDialog.showDialog()
        } else {
            LoadingDialog.dismissDialog()
        }
    }

    private fun showMessage(message: String) = show(applicationContext, message)

    private fun show(context: Context, text: CharSequence) {
        val toast = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    protected abstract fun setDataBindingLayout()

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected abstract fun setUpViewModel(): BaseViewModel

}