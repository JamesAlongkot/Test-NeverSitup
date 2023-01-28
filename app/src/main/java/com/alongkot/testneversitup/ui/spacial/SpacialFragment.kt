package com.alongkot.testneversitup.ui.spacial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alongkot.testneversitup.databinding.FragmentSpacialBinding

class SpacialFragment : Fragment() {

    private var _binding: FragmentSpacialBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val spacialViewModel =
            ViewModelProvider(this)[SpacialViewModel::class.java]

        _binding = FragmentSpacialBinding.inflate(inflater, container, false)
        binding.fibonacci.setOnClickListener {
            spacialViewModel.showFibonacciDialog(requireContext())
        }
        binding.prime.setOnClickListener {
            spacialViewModel.showPrimeDialog(requireContext())
        }
        binding.filterArray.setOnClickListener {
            spacialViewModel.showFilterArrayDialog(requireContext())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}