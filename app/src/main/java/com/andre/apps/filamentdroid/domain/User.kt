package com.andre.apps.filamentdroid.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class User(
    // Personal
    val username: String,
    val email: String,
    val phoneNumber: String,
    private val birthDate: Date?,
    // Address
    val country: String,
    val city: String,
    val streetName: String,
    val streetAddress: String,
    val zipCode: String,
    // Subscription
    val plan: String,
    val status: String,
    val paymentMethod: String,
    val term: String,
) {

    // If birth date is somehow empty, show empty value
    val birthDateAsString: String
        get() {
            return if (birthDate != null) {
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(
                    birthDate
                )
            } else ""
        }
}
