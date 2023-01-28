package com.alongkot.testneversitup.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Currency(
    var code: String,
    var rate: Double,
    var symbol: String,
    var update_timestamp: Long

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}