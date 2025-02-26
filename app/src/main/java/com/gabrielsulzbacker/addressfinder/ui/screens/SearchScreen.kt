package com.gabrielsulzbacker.addressfinder.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabrielsulzbacker.addressfinder.R
import com.gabrielsulzbacker.addressfinder.ui.viewmodels.AddressViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: AddressViewModel
) {
    var zipCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var neighborhood by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var isEnabled by remember { mutableStateOf(false) }
    val addressResponse by viewModel.addressResponse
    val isError by viewModel.isError
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(addressResponse, isError) {
        if (!isError) {
            address = addressResponse?.address ?: ""
            neighborhood = addressResponse?.neighborhood ?: ""
            city = addressResponse?.city ?: ""
            state = addressResponse?.state ?: ""
        } else {
            address = ""
            neighborhood = ""
            city = ""
            state = ""
        }
    }

    DisposableEffect(Unit) {
        onDispose { viewModel.resetData() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .clickable {
                        keyboardController?.hide()
                        navController.popBackStack()
                    }
                    .size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.lupa),
                contentDescription = "Logo",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(R.string.search_title),
                fontWeight = FontWeight(700),
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(R.string.search_text),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(14.dp))
            OutlinedTextField(
                value = zipCode,
                onValueChange = {
                    if (it.length <= 8) {
                        zipCode = it
                    }
                    if (zipCode.length == 8) {
                        viewModel.getAddressFromCep(zipCode)
                        keyboardController?.hide()
                    }
                },
                label = { Text(stringResource(R.string.search_label_zip_code)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            text = "CEP inválido!",
                            color = Color.Red
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(14.dp))
            CustomField(
                value = address,
                label = R.string.search_label_address,
                isEnabled = isEnabled
            )
            Spacer(modifier = Modifier.height(14.dp))
            CustomField(
                value = neighborhood,
                label = R.string.search_label_neighborhood,
                isEnabled = isEnabled
            )
            Spacer(modifier = Modifier.height(14.dp))
            CustomField(
                value = city,
                label = R.string.search_label_city,
                isEnabled = isEnabled
            )
            Spacer(modifier = Modifier.height(14.dp))
            CustomField(
                value = state,
                label = R.string.search_label_state,
                isEnabled = isEnabled
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomField(
    value: String,
    label: Int,
    isEnabled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(stringResource(label)) },
        enabled = false,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = colorResource(R.color.regular_gray),
            disabledLabelColor = colorResource(R.color.regular_gray),
            containerColor = if (!isEnabled) colorResource(R.color.soft_gray) else Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}