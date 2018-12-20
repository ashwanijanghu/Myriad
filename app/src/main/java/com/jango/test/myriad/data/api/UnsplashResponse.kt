package com.jango.test.myriad.data.api

import com.jango.test.myriad.data.model.UnsplashItem

/**
 * Wrapper class for the List of @{UnsplashItem} type object.
 * Used for deserialization of Json response to list of @{UnsplashItem}
 */
class UnsplashResponse : ArrayList<UnsplashItem>(){}