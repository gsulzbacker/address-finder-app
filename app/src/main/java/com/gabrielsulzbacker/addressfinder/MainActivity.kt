package com.gabrielsulzbacker.addressfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gabrielsulzbacker.addressfinder.navigation.NavGraph
import com.gabrielsulzbacker.addressfinder.ui.viewmodels.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AddressViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph(viewModel)
        }
    }
}