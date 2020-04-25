package com.airtel.model
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("data")
    var `data`: Data? = null,
    @SerializedName("requestId")
    var requestId: String? = null
)

data class Data(
    @SerializedName("addressList")
    var addressList: List<Address>? = null,
    @SerializedName("autoCompleteRequestString")
    var autoCompleteRequestString: String? = null
)

data class Address(
    @SerializedName("addressString")
    var addressString: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("label")
    var label: String? = null,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("pinCode")
    var pinCode: String? = null
)