package com.jango.test.myriad.data.api

/**
 * A generic warpper class for the response models. Can be used for other APIs object deserialization/serialization
 */
class APIResponse<T>(val status: String, val message: String, val data: List<T>)