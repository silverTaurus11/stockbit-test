package com.project.bibit_test.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("Message") val message: String?= "",
    @SerializedName("Type") val type: Int?= 0,
    @SerializedName("Metadata") val metadata: Metadata?= null,
    @SerializedName("Data") val data: List<CoinItem>?= null
)

data class Metadata(
    @SerializedName("Count") val count: Long
)

data class CoinItem(
    @SerializedName("CoinInfo") val coinInfo: CoinInfo?= null,
    @SerializedName("RAW") val rawInfo: RawInfo?= null
)

data class CoinInfo(
    @SerializedName("Id") val id: Int?= 0,
    @SerializedName("Name") val name: String?= "",
    @SerializedName("FullName") val fullName: String?= ""
)

data class RawInfo(
    @SerializedName("USD") val usdCurrencyItem: CurrencyItem
)

data class CurrencyItem(
    @SerializedName("MARKET") val market: String?= "",
    @SerializedName("FROMSYMBOL") val fromSymbol: String?= "",
    @SerializedName("TOSYMBOL") val toSymbol: String?= "",
    @SerializedName("PRICE") val price: Double?= 0.0,
    @SerializedName("CHANGEHOUR") val changeHour: Double?= 0.0,
    @SerializedName("CHANGEPCTHOUR") val changeHourPercent: Double?= 0.0
)