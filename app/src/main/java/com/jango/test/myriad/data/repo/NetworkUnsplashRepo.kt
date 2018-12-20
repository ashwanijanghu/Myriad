package com.jango.test.myriad.data.repo

import android.arch.lifecycle.LiveData
import com.jango.test.myriad.data.api.RestAPIClient
import com.jango.test.myriad.data.model.UnsplashItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Concrete implementation of @{UnsplashRepo} for the Network data fetch
 */
class NetworkUnsplashRepo : UnsplashRepo{
    val BASE_URL = "https://api.unsplash.com"
    val UNSPLASH_URL = "/photos/?client_id=f7af843e895c61a1f3434e6823743a08fb08ace46e203353f539a30eeb2a67e7&page="

    val bgScope = CoroutineScope(Dispatchers.IO)


    /**
     * Load Unsplash Objects containing images info
     * @param page Page index need to be fetched
     * @return List of @{UnsplashItem} objects
     */
    override fun loadUnsplashItems(page: Int): LiveData<List<UnsplashItem>> {

        val url = BASE_URL+UNSPLASH_URL+page

        return object : LiveData<List<UnsplashItem>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                bgScope.launch {
                    postValue(RestAPIClient.getResponse(url))
                }

            }
        }
    }
}