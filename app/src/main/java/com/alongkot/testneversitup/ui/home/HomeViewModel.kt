package com.alongkot.testneversitup.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.alongkot.testneversitup.global.CurrencyCode
import com.alongkot.testneversitup.global.Func
import com.alongkot.testneversitup.room.database.CurrencyDatabase
import com.alongkot.testneversitup.room.entity.Currency
import com.google.gson.Gson
import java.text.DecimalFormat
import java.time.temporal.TemporalAmount

@SuppressLint("StaticFieldLeak")
class HomeViewModel(application: Application,
                    private val context: Context) : AndroidViewModel(application) {

    private val _usd = MutableLiveData<Currency>()
    private val _gbp = MutableLiveData<Currency>()
    private val _eur = MutableLiveData<Currency>()
    private val _lastUpdate = MutableLiveData<String>()
    private val _exchangeAns = MutableLiveData<String>().apply {
        value = "Exchange to BTC: 0"
    }
    val lastUpdated: LiveData<String> = _lastUpdate
    val btcAmount: LiveData<String> = _exchangeAns
    val usd: LiveData<Currency> = _usd
    val gbp: LiveData<Currency> = _gbp
    val eur: LiveData<Currency> = _eur

    fun getCurrencyLiveData(viewLifecycleOwner: LifecycleOwner) {
        CurrencyDatabase(context)
            .currencyDao()
            .getLastCurrencyLiveData().observe(viewLifecycleOwner, Observer {
                setData(it)
            })
    }

    fun setData(list: List<Currency>?) {
        Log.w("lastdata",Gson().toJson(list))
        if (list != null) {
            for (data in list){
                when (data.code) {
                    CurrencyCode.USD -> {
                        _usd.postValue(data)
                    }
                    CurrencyCode.GBP -> {
                        _gbp.postValue(data)
                    }
                    CurrencyCode.EUR -> {
                        _eur.postValue(data)
                    }
                }
                _lastUpdate.postValue(Func().convertLongToDate(data.update_timestamp))
            }
        }
    }

    fun exchangeToBTC(amount: Double,currency: String):String{
        val exchangeRate = getRate(currency)
        val res: String = if (exchangeRate == 0.0) {
            "Please add amount"
        }else{
            val btcAmount = amount / exchangeRate
            "Exchange to BTC: " + DecimalFormat("#.#####").format(btcAmount)
        }

        _exchangeAns.postValue(res)
        return res
    }

    fun getRate(currency: String): Double {
        return when(currency){
            CurrencyCode.USD -> usd.value?.rate ?:0.0
            CurrencyCode.GBP -> gbp.value?.rate ?:0.0
            CurrencyCode.EUR -> eur.value?.rate ?:0.0
            else -> 0.0
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val application: Application,
        private val context: Context
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(
                application,
                context
            ) as T
        }
    }
}