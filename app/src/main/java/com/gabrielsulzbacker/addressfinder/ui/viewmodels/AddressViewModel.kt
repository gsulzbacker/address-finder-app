package com.gabrielsulzbacker.addressfinder.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielsulzbacker.addressfinder.repositories.AddressRepository
import com.gabrielsulzbacker.addressfinder.responses.AddressResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class AddressViewModel(private val repository: AddressRepository) : ViewModel() {
    private val _addressResponse = mutableStateOf<AddressResponse?>(null)
    val addressResponse: State<AddressResponse?> = _addressResponse

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    fun getAddressFromCep(cep: String) {
        _isError.value = false

        viewModelScope.launch {
            try {
                val response: Response<AddressResponse> = repository.getAddress(cep)
                if (response.isSuccessful) {
                    val address = response.body()
                    if (address?.error == true) {
                        _isError.value = true
                        _addressResponse.value = null
                    } else {
                        _addressResponse.value = address ?: AddressResponse()
                    }
                } else {
                    _isError.value = true
                    _addressResponse.value = null
                }
            } catch (e: Exception) {
                _isError.value = true
            }
        }
    }

    fun resetData() {
        _addressResponse.value = AddressResponse()
        _isError.value = false
    }
}