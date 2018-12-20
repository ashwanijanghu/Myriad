package com.jango.test.myriad.data.api

import com.google.gson.Gson
import com.jango.test.myriad.data.http.HttpRequest
import com.jango.test.myriad.data.http.HttpTask
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * Main Rest API gateway for the whole application. Ideally should be exposed to the repository only.
 *
 */
object RestAPIClient {

    /**
     * retrieves the resource of given type
     * @param url The end point from which data needs to be fetched
     * @return API response object
    </T> */
    fun getResponse(url: String): UnsplashResponse {
        val rawResponse = HttpTask.execute(HttpRequest(HttpRequest.Method.GET, url))
        return Gson().fromJson(rawResponse.response, UnsplashResponse::class.java)
    }

    /**
     * A generic extension to Parameterized type for deserialization of generic class objects
     */
    private fun getType(rawClass: Class<*>, parameterClass: Class<*>): Type {
        return object : ParameterizedType {
            override fun getRawType(): Type {
                return rawClass
            }

            override fun getOwnerType(): Type? {
                return null
            }

            override fun getActualTypeArguments(): Array<Type> {
                return arrayOf<Type>(parameterClass)
            }
        }
    }
}