package com.deloitte.base.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VBinding : ViewBinding, ViewModel : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: ViewModel
    lateinit var binding: VBinding

    private var mActivityProvider: ViewModelProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initViewModel()
    }

    abstract fun getViewBinding(): VBinding

    open fun initViewModel() {}

    protected fun <T : ViewModel> getActivityViewModel(modelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(modelClass)
    }
}

