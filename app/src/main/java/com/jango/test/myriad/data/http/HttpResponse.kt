package com.jango.test.myriad.data.http

/**
 * A wrapper around Json response from API server
 */
class HttpResponse (
    val responseCode: Int = 0,
    val response: String? = null)