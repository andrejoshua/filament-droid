package com.andre.apps.filamentdroid.data.network

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("uid") val uid: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("social_insurance_number") val socialInsuranceNumber: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("employment") val employment: EmploymentResponse,
    @SerializedName("address") val address: AddressResponse,
    @SerializedName("credit_card") val cc: CreditCardResponse,
    @SerializedName("subscription") val subscription: SubscriptionResponse,
)