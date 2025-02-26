package com.gabrielsulzbacker.addressfinder.services

import com.gabrielsulzbacker.addressfinder.responses.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
interface AddressService {
    @GET("{zipCode}/json/")
    suspend fun getAddress(@Path("zipCode") zipCode: String): Response<AddressResponse>
}