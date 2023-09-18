package com.andre.apps.filamentdroid.data.network

import com.andre.apps.filamentdroid.domain.Repository
import com.andre.apps.filamentdroid.domain.User
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: ApiService) : Repository {
    override suspend fun getUser(): User {
        return service.getUser().body()!!.asUserDTO()
    }
}