package com.alongkot.testneversitup

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alongkot.testneversitup.global.Func
import java.util.*

class AppMain : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
    }
    private val timer = Timer()
    private val task = object : TimerTask() {
        override fun run() {
            Func().getCurrencyPrices()
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        //Update every 1 min
        timer.schedule(task, 0, 60 * 1000)
    }
}