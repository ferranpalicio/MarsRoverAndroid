package com.pal.marsroverandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pal.marsroverandroid.data.DataReader

class ViewModelFactory(private val dataReader: DataReader) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(dataReader) as T
    }
}