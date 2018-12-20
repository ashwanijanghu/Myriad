package com.jango.test.myriad.data.http

/**
 * A simple Http Request format consune by dependents
 */
class HttpRequest {

    var method: Method? = null
    var url: String? = null
    var headers: HashMap<String, String>? = null
    var postData: String? = null

    val methodString: String
        get() = method!!.toString()

    interface RequestCallback {
        fun onResponse(r: HttpResponse)
    }

    enum class Method {
        GET,
        POST,
        UPDATE,
        DELETE
    }

    constructor(method: Method, URL: String) {
        this.method = method
        this.url = URL
    }

    constructor(method: Method, URL: String, postData: String) {
        this.method = method
        this.url = URL
        this.postData = postData
    }

}