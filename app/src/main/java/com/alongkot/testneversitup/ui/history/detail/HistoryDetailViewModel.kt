package com.alongkot.testneversitup.ui.history.detail

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.TextView
import androidx.lifecycle.*
import com.alongkot.testneversitup.room.database.CurrencyDatabase
import com.alongkot.testneversitup.room.entity.Currency

@SuppressLint("StaticFieldLeak")
class HistoryDetailViewModel(
    application: Application,
    private val context: Context,
    private val code: String
) : AndroidViewModel(application) {
    private val _list = MutableLiveData<List<Currency>>().apply {
        value = java.util.ArrayList<Currency>()
    }
    val listHistory: LiveData<List<Currency>> = _list

    fun getCurrencyHistoryLiveData(viewLifecycleOwner: LifecycleOwner) {
        CurrencyDatabase(context)
            .currencyDao()
            .getAllCurrencyByCodeLiveData(code)
            .observe(viewLifecycleOwner, Observer {
                setData(it)
            })
    }

    private fun setData(data: List<Currency>) {
        _list.postValue(data)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val application: Application,
        private val context: Context,
        private val code: String
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HistoryDetailViewModel(
                application,
                context,
                code
            ) as T
        }
    }
}