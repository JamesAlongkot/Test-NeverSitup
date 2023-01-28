package com.alongkot.testneversitup.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alongkot.testneversitup.room.entity.Currency
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrent(currency: Currency): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAllCurrent(currencyList: List<Currency>)

    @Query("delete from currency where id = :id")
    suspend fun deleteCurrency(id: Int): Int

    @Query("select * from currency where id = :id")
    suspend fun getCurrency(id: Int): Currency

    @Query("select * from currency where update_timestamp = :time")
    suspend fun getCurrencyByTime(time:Long): List<Currency>

    @Query("select * from currency order by id asc")
    fun getAllCurrencyLiveData(): LiveData<List<Currency>>

    @Query("select * from currency where code = :code order by id desc")
    fun getAllCurrencyByCodeLiveData(code: String): LiveData<List<Currency>>

    @Query("select * from currency order by id desc limit 3")
    fun getLastCurrencyLiveData(): LiveData<List<Currency>>

    @Update()
    suspend fun updateCurrency(currency: Currency)

    @Query("delete from currency")
    suspend fun clearCurrency()

}