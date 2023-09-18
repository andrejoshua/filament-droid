package com.andre.apps.filamentdroid.data.network

import com.google.gson.annotations.SerializedName

data class SubscriptionResponse(
    @SerializedName("plan") val plan: String,
    @SerializedName("status") val status: String,
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("term") val term: String,
)
