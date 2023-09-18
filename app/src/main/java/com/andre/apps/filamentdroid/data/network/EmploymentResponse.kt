package com.andre.apps.filamentdroid.data.network

import com.google.gson.annotations.SerializedName

data class EmploymentResponse(
    @SerializedName("title") val title: String,
    @SerializedName("key_skill") val keySkill: String
)
