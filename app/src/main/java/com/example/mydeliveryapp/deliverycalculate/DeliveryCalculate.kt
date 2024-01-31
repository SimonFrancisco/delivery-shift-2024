package com.example.mydeliveryapp.deliverycalculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydeliveryapp.databinding.FragmentDeliveryCalculateBinding

class DeliveryCalculate : Fragment() {
    private var _binding: FragmentDeliveryCalculateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DeliveryCalculateViewModel by lazy {
        ViewModelProvider(this)[DeliveryCalculateViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryCalculateBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}