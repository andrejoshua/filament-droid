package com.andre.apps.filamentdroid.data.network

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("city") val city: String,
    @SerializedName("street_name") val streetName: String,
    @SerializedName("street_address") val streetAddress: String,
    @SerializedName("zip_code") val zipCode: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("coordinates") val coordinates: CoordinatesResponse,
)
