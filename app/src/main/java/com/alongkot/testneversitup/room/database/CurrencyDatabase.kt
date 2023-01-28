package com.alongkot.testneversitup.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alongkot.testneversitup.room.dao.CurrencyDao
import com.alongkot.testneversitup.room.entity.Currency

@Database(entities = [Currency::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var instance: CurrencyDatabase? = null

        private const val DATABASE_NAME = "currencyDatabase"

        operator fun invoke(context: Context) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        ).also {
                            instance = it
                        }
                }

        private fun buildDatabase(context: Context): CurrencyDatabase {
            return Room.databaseBuilder(
                context,
                CurrencyDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}