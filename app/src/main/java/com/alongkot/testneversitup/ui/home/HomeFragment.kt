package com.alongkot.testneversitup.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alongkot.testneversitup.R
import com.alongkot.testneversitup.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        factory = HomeViewModel.Factory(
            requireActivity().application,
            requireActivity()
        )

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val homeViewModel =
            ViewModelProvider(this,factory)[HomeViewModel::class.java]
        binding.viewmodel = homeViewModel
        binding.lifecycleOwner = this

        homeViewModel.getCurrencyLiveData(viewLifecycleOwner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.exchange.spinner.adapter = adapter
        }

        binding.exchange.amount.addTextChangedListener {
            var d = 0.0
            if (it.toString().isNotEmpty()){
               d = it.toString().toDouble()
            }
            homeViewModel.exchangeToBTC(d,
                binding.exchange.spinner.adapter.getItem(binding.exchange.spinner.selectedItemPosition) as String
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}