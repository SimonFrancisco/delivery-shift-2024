package com.example.mydeliveryapp.deliverycalculate

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydeliveryapp.R
import com.example.mydeliveryapp.databinding.FragmentDeliveryCalculateBinding

class DeliveryCalculate : Fragment() {
    private var _binding: FragmentDeliveryCalculateBinding? = null
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
        var typeList : List<String>
        viewModel.deliveryPoints.observe(viewLifecycleOwner){cities ->
            citiesList = cities[0].points.map { it.name }
            val citiesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item,citiesList)
            binding.cityAutoComplete.setAdapter(citiesAdapter)
            binding.citySendAutoComplete.setAdapter(citiesAdapter)
        }
        viewModel.deliveryTypes.observe(viewLifecycleOwner){types->
            typeList = types[0].packages.map { it.name }
            val typeAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,typeList)
            binding.itemAutoComplete.setAdapter(typeAdapter)
        }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT




        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}