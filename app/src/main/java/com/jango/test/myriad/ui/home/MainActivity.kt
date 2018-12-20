package com.jango.test.myriad.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jango.test.myriad.common.extensions.inTransaction
import com.jango.test.myriad.R
import com.jango.test.myriad.ui.home.fragments.UnsplashListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchUnsplashListFragment()
    }

    private fun launchUnsplashListFragment() {
        supportFragmentManager.inTransaction {
            add(R.id.myriadView, UnsplashListFragment.newInstance())
        }
    }
}
