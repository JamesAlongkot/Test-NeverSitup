package com.alongkot.testneversitup.model.currentprice

import com.google.gson.annotations.SerializedName

data class CurrentpriceResponse(

	@field:SerializedName("chartName")
	val chartName: String? = null,

	@field:SerializedName("bpi")
	val bpi: Bpi? = null,

	@field:SerializedName("time")
	val time: Time? = null,

	@field:SerializedName("disclaimer")
	val disclaimer: String? = null
)

data class EUR(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("rate_float")
	val rateFloat: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("rate")
	val rate: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class Time(

	@field:SerializedName("updateduk")
	val updateduk: String? = null,

	@field:SerializedName("updatedISO")
	val updatedISO: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null
)

data class USD(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("rate_float")
	val rateFloat: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("rate")
	val rate: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class BpiDetail(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("rate_float")
	val rateFloat: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("rate")
	val rate: String? = null,

	@field:SerializedName("description")
	val description: String? = null
) {
	fun getRateDouble(): Double {
		return rate?.replace(",","")?.toDouble()!!
	}
}

data class Bpi(

	@field:SerializedName("EUR")
	val eUR: BpiDetail? = null,

	@field:SerializedName("GBP")
	val gBP: BpiDetail? = null,

	@field:SerializedName("USD")
	val uSD: BpiDetail? = null
)
