package com.gabrielsulzbacker.addressfinder.repositories

import com.gabrielsulzbacker.addressfinder.responses.AddressResponse
import com.gabrielsulzbacker.addressfinder.services.AddressService
import retrofit2.Response

class AddressRepository(
    private val service: AddressService
) {
    suspend fun getAddress(cep: String): Response<AddressResponse> {
        return service.getAddress(cep)
    }
}