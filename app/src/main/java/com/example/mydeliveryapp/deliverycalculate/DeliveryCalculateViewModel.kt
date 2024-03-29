package com.example.mydeliveryapp.deliverycalculate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydeliveryapp.networkdelivery.CalculateBody
import com.example.mydeliveryapp.networkdelivery.DeliveryApi
import com.example.mydeliveryapp.networkdelivery.DeliveryCalculateList
import com.example.mydeliveryapp.networkdelivery.DeliveryPoints
import com.example.mydeliveryapp.networkdelivery.DeliveryTypes
import kotlinx.coroutines.launch

class DeliveryCalculateViewModel : ViewModel() {
    private val _responsePoints = MutableLiveData<String>()
    val responsePoints:LiveData<String>
        get() = _responsePoints
    private val _responseTypes = MutableLiveData<String>()
    val responseTypes:LiveData<String>
        get() = _responseTypes

    private val _responseCalculate = MutableLiveData<String>()
    val responseCalculate:LiveData<String>
        get() = _responseCalculate

    private val _deliveryPoint = MutableLiveData<List<DeliveryPoints>>()
    val deliveryPoints:LiveData<List<DeliveryPoints>>
        get() = _deliveryPoint

    private val _deliveryTypes = MutableLiveData<List<DeliveryTypes>>()
    val deliveryTypes:LiveData<List<DeliveryTypes>>
        get() = _deliveryTypes

    private val _deliveryCalculate = MutableLiveData<List<DeliveryCalculateList>>()

    val deliveryCalculate:LiveData<List<DeliveryCalculateList>>
        get() = _deliveryCalculate

    init {
        getDeliveryPoints()
        getDeliveryTypes()
    }


    fun getDeliveryCalculate(calculateBody: CalculateBody) {
        viewModelScope.launch {
            try {
                val listResult = DeliveryApi.retrofitService.getCalculation(calculateBody)
                _deliveryCalculate.value = listOf(listResult)
                _responseCalculate.value = "Success: ${listResult.options[0].price}"
            }
            catch (e:Exception){
                _responseCalculate.value = "Error: ${e.message}"
                _deliveryCalculate.value = emptyList()
            }
        }
    }

    private fun getDeliveryTypes() {
        viewModelScope.launch {
            try {
                val listResult = DeliveryApi.retrofitService.getTypes()
                _deliveryTypes.value = listOf(listResult)
                _responseTypes.value = "Success: ${listResult.packages.size} packages"
            }
            catch (e:Exception){
                _responseTypes.value = "Error: ${e.message}"
                _deliveryTypes.value = emptyList()
            }
        }
    }

    private fun getDeliveryPoints() {
        viewModelScope.launch {
            try {
                val listResult = DeliveryApi.retrofitService.getPoints()
                _deliveryPoint.value = listOf(listResult)
                _responsePoints.value = "Success: ${listResult.points.size} points"
            }
            catch (e:Exception){
                _responsePoints.value = "Error: ${e.message}"
                _deliveryPoint.value = emptyList()
            }
        }
    }
}