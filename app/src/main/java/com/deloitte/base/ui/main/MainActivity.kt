package com.deloitte.base.ui.main

import android.os.Bundle
import com.deloitte.base.R
import com.deloitte.base.databinding.ActivityMainBinding
import com.deloitte.base.ui.base.BaseActivity
import com.deloitte.base.ui.list.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private var mainViewModel: MainViewModel? = null

    override fun initViewModel() {
        mainViewModel = getActivityViewModel(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.navigation_host,ListFragment())
                .commit()
        }
    }

}