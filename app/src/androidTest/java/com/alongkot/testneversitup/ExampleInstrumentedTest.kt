package com.alongkot.testneversitup

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alongkot.testneversitup.global.CurrencyCode
import com.alongkot.testneversitup.room.entity.Currency
import com.alongkot.testneversitup.ui.home.HomeViewModel
import com.alongkot.testneversitup.ui.spacial.SpacialViewModel
import com.google.gson.Gson

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val mockData =
        "[{\"code\":\"USD\",\"id\":552,\"rate\":22988.3189,\"symbol\":\"\\u0026#36;\",\"update_timestamp\":1674826800000},{\"code\":\"GBP\",\"id\":551,\"rate\":19208.8554,\"symbol\":\"\\u0026pound;\",\"update_timestamp\":1674826800000},{\"code\":\"EUR\",\"id\":550,\"rate\":22393.9789,\"symbol\":\"\\u0026euro;\",\"update_timestamp\":1674826800000}]"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.alongkot.testneversitup", appContext.packageName)
    }

    @Test
    fun exchange_isCorrect() {
        val application = ApplicationProvider.getApplicationContext() as Application
        val context = ApplicationProvider.getApplicationContext() as Context
        val model = HomeViewModel(application,context)

        val currencies: List<Currency> = Gson().fromJson(mockData, Array<Currency>::class.java).toList()
        model.setData(currencies)
        val usd = model.getRate(CurrencyCode.USD)
        val amount = model.exchangeToBTC(100.0,CurrencyCode.USD)

        assertEquals(22988.3189, usd,0.0)
        assertEquals("0.00435" , amount )
    }

    @Test
    fun isPrime_isCorrect() {
        val model = SpacialViewModel()
        assertEquals(true, model.isPrime(13))
        assertEquals(true, model.isPrime(11))
        assertEquals(true, model.isPrime(7))
        assertEquals(true, model.isPrime(5))
        assertEquals(true, model.isPrime(3))
        assertEquals(true, model.isPrime(2))
    }

    @Test
    fun isPrime_isInCorrect() {
        val model = SpacialViewModel()
        assertEquals(false, model.isPrime(1))
        assertEquals(false, model.isPrime(4))
    }

    @Test
    fun fibonacci_isCorrect() {
        val model = SpacialViewModel()
        assertEquals("[0, 1, 1, 2]", model.generateFibonacci(4))
        assertEquals("[0, 1, 1, 2, 3]", model.generateFibonacci(5))
    }

    @Test
    fun filterArray_isCorrect() {
        val model = SpacialViewModel()
        assertEquals(arrayListOf(2), model.filter(arrayListOf(1,2,3), arrayListOf(2,4,5,6)))
    }
}