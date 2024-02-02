package com.example.mydeliveryapp.sendingoption

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydeliveryapp.networkdelivery.DeliveryCalculateList

@Suppress("UNCHECKED_CAST")
class SendOptionViewModelFactory(private val deliveryOption:DeliveryCalculateList):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SendOptionViewModel::class.java)){
            return SendOptionViewModel(deliveryOption) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}