package com.andre.apps.filamentdroid.data.network

import com.google.gson.annotations.SerializedName

data class CoordinatesResponse(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
)
