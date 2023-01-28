package com.alongkot.testneversitup.ui.history.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alongkot.testneversitup.databinding.FragmentHistoryDetailBinding
import com.alongkot.testneversitup.ui.history.detail.adapter.HistoryDetailAdapter
import com.alongkot.testneversitup.ui.home.HomeViewModel

class HistoryDetailFragment(val code: String) : Fragment() {

    private var _binding: FragmentHistoryDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: HistoryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = HistoryDetailViewModel.Factory(
            requireActivity().application,
            requireActivity(),
            code
        )
        viewModel = ViewModelProvider(viewModelStore,factory)[HistoryDetailViewModel::class.java]
        _binding = FragmentHistoryDetailBinding.inflate(inflater, container, false)

        //region RecyclerView
        val adapter = HistoryDetailAdapter(ArrayList())

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rv.layoutManager = layoutManager
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.rv.adapter = adapter
        //endregion

        viewModel.getCurrencyHistoryLiveData(viewLifecycleOwner)
        viewModel.listHistory.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}