package com.alongkot.testneversitup.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.alongkot.testneversitup.R
import com.alongkot.testneversitup.databinding.FragmentHistoryBinding
import com.alongkot.testneversitup.global.CurrencyCode
import com.alongkot.testneversitup.ui.history.adapter.FragmentAdapter
import com.alongkot.testneversitup.ui.history.detail.HistoryDetailFragment
import com.google.android.material.tabs.TabLayout
import java.util.*

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val historyViewModel =
            ViewModelProvider(this)[HistoryViewModel::class.java]

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        val fragments = ArrayList<Fragment>()

        fragments.add(HistoryDetailFragment(CurrencyCode.USD))
        fragments.add(HistoryDetailFragment(CurrencyCode.EUR))
        fragments.add(HistoryDetailFragment(CurrencyCode.GBP))

        val pagerAdapter =
            FragmentAdapter(requireActivity().supportFragmentManager, requireContext(), fragments)
        binding.pager.adapter = pagerAdapter

        binding.tabLayout.setupWithViewPager(binding.pager)
        binding.tabLayout.getTabAt(0)?.text = getString(R.string.btc) + CurrencyCode.USD
        binding.tabLayout.getTabAt(1)?.text = getString(R.string.btc) + CurrencyCode.EUR
        binding.tabLayout.getTabAt(2)?.text = getString(R.string.btc) + CurrencyCode.GBP

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}