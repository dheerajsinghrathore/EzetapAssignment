package com.android.dynamiclayout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dynamiclayout.model.CustomResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getCustomUIApi()
        }
    }

    val users: LiveData<CustomResponse>
        get() = mainRepository.customResponse
}