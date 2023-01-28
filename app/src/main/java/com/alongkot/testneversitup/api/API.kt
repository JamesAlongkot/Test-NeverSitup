package com.alongkot.testneversitup.api

import com.alongkot.testneversitup.BuildConfig
import com.alongkot.testneversitup.api.enum.Method
import okhttp3.Headers
import okhttp3.Request
import okhttp3.RequestBody

class API {

    //region API

    //Get Currency prices
    fun CurrencyPrices(): Request {
        val url: String = BuildConfig.BASE_API_URL + "bpi/currentprice.json"
        return createRequest(Method.GET, url)
    }
    //endregion

    //region create request
    private fun createRequest(
        method: String,
        url: String
    ): Request {
        return createRequest(method, url, Headers.headersOf(),null)

    }private fun createRequest(
        method: String,
        url: String,
        headers: Headers,
        body: RequestBody?
    ): Request {
        val builder = Request.Builder().method(method,body).headers(headers).url(url)
        return builder.build()
    }
    //endregion
}