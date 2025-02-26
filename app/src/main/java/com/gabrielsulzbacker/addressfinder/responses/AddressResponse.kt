package com.gabrielsulzbacker.addressfinder.responses

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("cep") val zipCode: String = "",
    @SerializedName("logradouro") val address: String = "",
    @SerializedName("bairro") val neighborhood: String = "",
    @SerializedName("localidade") val city: String = "",
    @SerializedName("uf") val state: String = "",
    @SerializedName("erro") val error: Boolean = false
)