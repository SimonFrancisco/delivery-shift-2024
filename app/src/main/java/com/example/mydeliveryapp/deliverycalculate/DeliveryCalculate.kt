package com.example.mydeliveryapp.deliverycalculate

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mydeliveryapp.R
import com.example.mydeliveryapp.databinding.FragmentDeliveryCalculateBinding
import com.example.mydeliveryapp.networkdelivery.CalculateBody
import com.example.mydeliveryapp.networkdelivery.Package
import com.example.mydeliveryapp.networkdelivery.ReceiverPoint
import com.example.mydeliveryapp.networkdelivery.SenderPoint

class DeliveryCalculate : Fragment() {
    private var _binding: FragmentDeliveryCalculateBinding? = null
    private lateinit var packageDetails: Package
    private lateinit var receiverPoint: ReceiverPoint
    private lateinit var senderPoint: SenderPoint

    private val binding get() = _binding!!
    private val viewModel: DeliveryCalculateViewModel by lazy {
        ViewModelProvider(this)[DeliveryCalculateViewModel::class.java]
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryCalculateBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        var citiesList: List<String>
        var typeList: List<String>
        viewModel.deliveryPoints.observe(viewLifecycleOwner) { cities ->
            citiesList = cities[0].points.map { it.name }
            val citiesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, citiesList)
            binding.cityAutoComplete.setAdapter(citiesAdapter)
            binding.citySendAutoComplete.setAdapter(citiesAdapter)

        }
        viewModel.deliveryTypes.observe(viewLifecycleOwner) { types ->
            typeList = types[0].packages.map { it.name }
            val typeAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, typeList)
            binding.itemAutoComplete.setAdapter(typeAdapter)
        }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.calculateButton.setOnClickListener {
            viewModel.deliveryPoints.observe(viewLifecycleOwner) { cities ->
                cities[0].points.let {
                    for (city in it) {
                        if (city.name == binding.cityAutoComplete.text.toString()) {
                            senderPoint =
                                SenderPoint(city.latitude.toString(), city.longitude.toString())
                        }
                        if (city.name == binding.citySendAutoComplete.text.toString()) {
                            receiverPoint =
                                ReceiverPoint(city.latitude.toString(), city.longitude.toString())
                        }
                    }
                }
            }
            viewModel.deliveryTypes.observe(viewLifecycleOwner) { types ->
                types[0].packages.let {
                    for (names in it) {
                        if (names.name == binding.itemAutoComplete.text.toString()) {
                            packageDetails =
                                Package(
                                    names.height.toString(),
                                    names.height.toString(),
                                    names.weight.toString(),
                                    names.width.toString()
                                )
                        }
                    }
                }
            }
            if (binding.cityAutoComplete.text.isNotEmpty() && binding.citySendAutoComplete.text.isNotEmpty() && binding.itemAutoComplete.text.isNotEmpty()) {
                viewModel.getDeliveryCalculate(
                    CalculateBody(
                        packageDetails,
                        senderPoint,
                        receiverPoint
                    )
                )
                viewModel.deliveryCalculate.observe(viewLifecycleOwner){
                        calculate ->
                    val action = DeliveryCalculateDirections.actionDeliveryCalculateToSendOption(calculate[0])
                    findNavController().navigate(action)
                }
            }
        }


        //binding.calculateButton.text = viewModel.responseCalculate.value


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}