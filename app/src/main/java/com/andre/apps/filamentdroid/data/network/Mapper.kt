package com.andre.apps.filamentdroid.data.network

import com.andre.apps.filamentdroid.domain.User
import java.text.SimpleDateFormat
import java.util.Locale

fun UserResponse.asUserDTO(): User {
    val beFormat = "yyyy-MM-dd" // Example: 1966-04-13
    val birthDate = SimpleDateFormat(
        beFormat,
        Locale.getDefault()
    ).parse(dateOfBirth)
    return User(
        username = username,
        email = email,
        phoneNumber = phoneNumber,
        birthDate = birthDate,
        country = address.country,
        city = address.city,
        streetName = address.streetName,
        streetAddress = address.streetAddress,
        zipCode = address.zipCode,
        plan = subscription.plan,
        status = subscription.status,
        paymentMethod = subscription.paymentMethod,
        term = subscription.term,
    )
}