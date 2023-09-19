package com.andre.apps.filamentdroid.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ViewModel() {

    fun getSelectedPosition(): LiveData<Int> = _selectedPosition

    private val _selectedPosition = MutableLiveData<Int>()

    fun selectItem(position: Int) {
        _selectedPosition.value = position
    }
}