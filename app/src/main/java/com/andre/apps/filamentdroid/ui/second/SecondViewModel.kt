package com.andre.apps.filamentdroid.ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andre.apps.filamentdroid.domain.GetUserUseCase
import com.andre.apps.filamentdroid.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    ViewModel() {

    fun getUser(): LiveData<User> = _user

    private val _user = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            val user = getUserUseCase.execute()
            _user.postValue(user)
        }
    }
}