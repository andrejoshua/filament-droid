package com.andre.apps.filamentdroid.domain

interface Repository {

    suspend fun getUser(): User
}