package com.imams.animalia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(

): ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun isFavorite() {
        viewModelScope.launch {
            delay(1000)
            _isFavorite.postValue(false)
        }
    }

    fun setFavorite(value: Boolean) {
        viewModelScope.launch {
            _isFavorite.postValue(value)
        }
    }

}