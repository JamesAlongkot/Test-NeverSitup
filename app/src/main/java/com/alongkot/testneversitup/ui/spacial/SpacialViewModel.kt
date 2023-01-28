package com.alongkot.testneversitup.ui.spacial

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.alongkot.testneversitup.R

class SpacialViewModel : ViewModel() {

    /**
     * This method is used to generate fibonacci number
     * @param n is maximum round
     */
    fun generateFibonacci(n: Int): String {
        if(n < 0) return ""
        val fibonacci = mutableListOf<Int>()
        var first = 0
        var second = 1
        fibonacci.add(first)
        if(n>1) {
            fibonacci.add(second)
        }
        for (i in 2 until n) {
            val next = first + second
            fibonacci.add(next)
            first = second
            second = next
        }
        return fibonacci.toString()
    }

    //region Primes
    /**
     * generate primes number
     */
    fun generatePrimes(n: Int): String {
        val primes = mutableListOf<Int>()
        for (i in 2..n){
            if (isPrime(i)) {
                primes.add(i)
            }
        }
        return primes.toString()
    }

    fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        for (i in 2..num/2) {
            if (num % i == 0) {
                return false
            }
        }
        return true
    }
    //endregion

    fun filter(arr1: List<Int>, arr2: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        for (n1 in arr1) {
            for (n2 in arr2) {
                if (n1 == n2) {
                    result.add(n1)
                    break
                }
            }
        }
        return result
    }

    private fun showDialog(context: Context,title: String ,msg: String) {
        val builder = AlertDialog.Builder(context, R.style.AlertDialog)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    fun showFibonacciDialog(context: Context) {
        val round = 10
        showDialog(context,
            "Fibonacci $round round",
            generateFibonacci(round))
    }

    fun showPrimeDialog(context: Context) {
        val round = 10
        showDialog(context,
            "Primes 2 - $round",
            generatePrimes(round))
    }

    fun showFilterArrayDialog(context: Context) {
        var arr1 = arrayListOf(1,2,3,9)
        val arr2 = arrayListOf(2,3,4,5,6,7)
        val arr3 = filter(arr1, arr2) as ArrayList<Int>
        showDialog(context,
            "Filter Array between arr1 = $arr1 & arr2 = $arr2",
            arr3.toString())
        arr1 = arr3
    }
}