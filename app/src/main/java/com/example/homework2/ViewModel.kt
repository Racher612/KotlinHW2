package com.example.homework2

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val urls = mutableStateOf(emptyList<String>())
    val ExternalUrls : State<List<String>> = urls

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun load() = effect {
        _isLoading.value = true
        urls.value = urls.value + connector.imageLink()
        _isLoading.value = false
        Log.d("load", urls.toString())
    }

    private fun effect(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block()
        }
    }
}