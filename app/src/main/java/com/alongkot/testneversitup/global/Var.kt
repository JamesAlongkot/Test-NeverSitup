package com.alongkot.testneversitup.global

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class Var {
    companion object{
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build()
    }

}