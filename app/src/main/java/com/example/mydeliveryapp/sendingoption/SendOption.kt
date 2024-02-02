package com.example.mydeliveryapp.sendingoption

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mydeliveryapp.R
import com.example.mydeliveryapp.databinding.FragmentDeliveryCalculateBinding
import com.example.mydeliveryapp.databinding.FragmentSendOptionBinding
import com.example.mydeliveryapp.deliverycalculate.DeliveryCalculateViewModel
import com.example.mydeliveryapp.networkdelivery.Package
import com.example.mydeliveryapp.networkdelivery.ReceiverPoint
import com.example.mydeliveryapp.networkdelivery.SenderPoint

class SendOption : Fragment() {
    private var _binding: FragmentSendOptionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SendOptionViewModel by lazy {
        ViewModelProvider(this)[SendOptionViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendOptionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val sendOption = SendOptionArgs.fromBundle(requireArguments()).calculatePay
        val viewModelFactory = SendOptionViewModelFactory(sendOption)
        binding.viewModel = ViewModelProvider(this,viewModelFactory)[SendOptionViewModel::class.java]

        val adapter = SendingOptionAdapter()
        val recyclerView = binding.sendRecyclerView
        recyclerView.adapter = adapter
        viewModel.deliveryOptionList.observe(viewLifecycleOwner){
            send-> adapter.setData(send.options)
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}