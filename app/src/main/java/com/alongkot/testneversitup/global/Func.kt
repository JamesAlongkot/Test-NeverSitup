@file:OptIn(DelicateCoroutinesApi::class)

package com.alongkot.testneversitup.global

import android.annotation.SuppressLint
import android.util.Log
import com.alongkot.testneversitup.AppMain.Companion.context
import com.alongkot.testneversitup.api.API
import com.alongkot.testneversitup.model.currentprice.CurrentpriceResponse
import com.alongkot.testneversitup.room.database.CurrencyDatabase
import com.alongkot.testneversitup.room.entity.Currency
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.text.SimpleDateFormat
import java.util.*

class Func() {

    fun getCurrencyPrices() {
        //Call current price
        val request = API().CurrencyPrices()

        Var.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {
                    if (response.code == HttpURLConnection.HTTP_OK){
                        val responseBody = response.body
                        val mediaType = responseBody?.contentType()
                        if (mediaType?.subtype == "javascript") {
                            val data = responseBody.string()
                            val json = Gson().fromJson(data, CurrentpriceResponse::class.java)
                            GlobalScope.launch {
                                addMultipleCurrencyToRoom(setDataFromApi(json))
                                Log.e("CurrencyPrices", data)
                            }.start()
                        } else {
                            Log.e("CurrencyPrices", "Response is not javascript")
                        }
                    }else{
                        Log.e("CurrencyPrices", response.code.toString())
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        })
    }

    private suspend fun setDataFromApi(data: CurrentpriceResponse?): List<Currency> {
        if (data == null) return emptyList()
        if (checkHas(data))return emptyList()
        val time = convertStringToLong(data.time?.updated)
        return listOf(
            Currency(
                CurrencyCode.EUR,
                data.bpi?.eUR?.getRateDouble()!!,
                data.bpi.eUR.symbol.toString(),
                time
            ),
            Currency(
                CurrencyCode.GBP,
                data.bpi.gBP?.getRateDouble()!!,
                data.bpi.gBP.symbol.toString(),
                time
            ),
            Currency(
                CurrencyCode.USD,
                data.bpi.uSD?.getRateDouble()!!,
                data.bpi.uSD.symbol.toString(),
                time
            ),
        )
    }

    private suspend fun checkHas(data: CurrentpriceResponse): Boolean {
        val myValue = GlobalScope.async {
            val list = CurrencyDatabase(context)
                .currencyDao()
                .getCurrencyByTime(convertStringToLong(data.time?.updated))
            return@async list.isNotEmpty()
        }.await()
        return myValue
    }

    private fun convertStringToLong(dateString: String?): Long {
        return convertStringToDate(dateString)?.time ?: 0
    }
    private fun convertStringToDate(dateString: String?): Date? {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.ENGLISH)
        return dateString?.let { dateFormat.parse(it) }
    }
    @SuppressLint("SimpleDateFormat")
    fun convertLongToDate(timestamp: Long, format:String = "dd/MM/yyyy HH:mm:ss", timeZone:TimeZone = TimeZone.getTimeZone("GMT+7")): String {
        val date = Date(timestamp)
        val formatter = SimpleDateFormat(format)
        formatter.timeZone = timeZone
        return formatter.format(date)
    }

    fun addMultipleCurrencyToRoom(currencyList: List<Currency>) {
        GlobalScope.launch {
            CurrencyDatabase(context)
                .currencyDao()
                .inserAllCurrent(currencyList)
        }
    }
}