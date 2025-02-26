package com.gabrielsulzbacker.addressfinder.di

import com.gabrielsulzbacker.addressfinder.repositories.AddressRepository
import com.gabrielsulzbacker.addressfinder.services.AddressService
import com.gabrielsulzbacker.addressfinder.ui.viewmodels.AddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(AddressService::class.java) }
    single { AddressRepository(get()) }

    viewModel {
        AddressViewModel(get())
    }
}