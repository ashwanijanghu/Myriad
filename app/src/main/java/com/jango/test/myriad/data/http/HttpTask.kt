package com.jango.test.myriad.data.http

import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


/**
 * Main Http executor, create connection with server and return the Json response.
 * By default this API will execute on @{UIThread}. Caller need to maintain the thread context
 */
object HttpTask {

    fun execute(request: HttpRequest): HttpResponse{
        val url: URL
        var urlConnection: HttpURLConnection? = null
        var response = HttpResponse()

        try {
            if (request == null || request!!.url == null || request!!.method == null) {
                Log.e("HttpTask", "BAD HttpRequest")
                throw Exception()
            }
            url = URL(request!!.url)
            urlConnection = url.openConnection() as HttpURLConnection
            Log.v("HttpTask", request!!.method?.name)

            urlConnection!!.setRequestMethod(request!!.method?.name)

            if (request!!.headers != null) {
                for (pair in request!!.headers!!.entries) {
                    urlConnection!!.setRequestProperty(pair.key, pair.value)
                }
            }
            urlConnection!!.setConnectTimeout(10000)
            urlConnection!!.setReadTimeout(10000)
            if (request!!.postData != null) {
                urlConnection!!.setDoOutput(true)
                val wr = DataOutputStream(urlConnection!!.getOutputStream())
                val postData = request!!.postData!!.toByteArray()
                wr.write(postData)
            }
            urlConnection!!.connect()

            val responseCode = urlConnection!!.getResponseCode()
            val responseString: String
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                responseString = readStream(urlConnection!!.getInputStream())
            } else {
                responseString = readStream(urlConnection!!.getErrorStream())
            }
            Log.v("HttpTask", "Response code:$responseCode")
            Log.v("HttpTask", responseString)
            response = HttpResponse(responseCode, responseString)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (urlConnection != null)
                urlConnection!!.disconnect()
        }

        return response
    }

    private fun readStream(inputStream: InputStream): String {
        var reader: BufferedReader? = null
        val response = StringBuffer()
        try {
            reader = BufferedReader(InputStreamReader(inputStream))
            var line = reader!!.readLine()
            while (line != null) {
                response.append(line)
                line = reader!!.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return response.toString()
    }
}