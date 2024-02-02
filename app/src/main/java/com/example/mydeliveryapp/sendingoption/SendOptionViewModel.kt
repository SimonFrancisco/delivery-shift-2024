package com.example.mydeliveryapp.sendingoption

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydeliveryapp.networkdelivery.DeliveryCalculateList

class SendOptionViewModel(deliveryOption: DeliveryCalculateList) : ViewModel() {

    private val _deliveryOptionList = MutableLiveData<DeliveryCalculateList>()

    val deliveryOptionList : LiveData<DeliveryCalculateList>
        get() = _deliveryOptionList

    init {
        _deliveryOptionList.value = deliveryOption
    }

}