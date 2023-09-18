package com.andre.apps.filamentdroid.domain

import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(): User {
        return repository.getUser()
    }
}