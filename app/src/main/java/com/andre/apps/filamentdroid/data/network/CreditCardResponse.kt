package com.andre.apps.filamentdroid.data.network

import com.google.gson.annotations.SerializedName

data class CreditCardResponse(
    @SerializedName("cc_number") val number: String,
)
