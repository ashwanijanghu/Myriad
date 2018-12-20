package com.jango.test.myriad.ui.home.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.jango.test.myriad.data.model.UnsplashItem
import com.jango.test.myriad.data.repo.Repo
import com.jango.test.myriad.data.repo.RepoFactoryImpl

/**
 * ViewModel implementation for the Unsplash fragment.
 * This class will be responsible for all data and busniess logic related to consumption of Unsplash item objects
 */
class UnsplashViewModel: ViewModel(){

    val pageIndex = MutableLiveData<Int>()
    var unsplashItemList = mutableListOf<UnsplashItem>()

    val unsplashItems: LiveData<List<UnsplashItem>> = Transformations
        .switchMap(pageIndex){pageIndex ->
            var offset = 0
            if(pageIndex >= 0){
                offset = pageIndex
            }
            RepoFactoryImpl.getRepository(Repo.NETWORK_REPO).loadUnsplashItems(offset)
        }
}