package com.jango.test.myriad.data.repo

import android.arch.lifecycle.LiveData
import com.jango.test.myriad.data.api.APIResponse
import com.jango.test.myriad.data.model.UnsplashItem

/**
 * Contract each repository must satisfy
 */
interface UnsplashRepo {

    /**
     * Load Unsplash Objects containing images info
     * @param page Page index need to be fetched
     * @return List of @{UnsplashItem} objects
     */
    fun loadUnsplashItems(page:Int): LiveData<List<UnsplashItem>>
}