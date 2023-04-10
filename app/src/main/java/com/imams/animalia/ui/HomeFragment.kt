package com.imams.animalia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.imams.animalia.GroupAnimalAdapter
import com.imams.animalia.databinding.FragmentHomeBinding
import com.imams.animalia.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter: GroupAnimalAdapter by lazy {
        GroupAnimalAdapter(callback = {

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLiveData()
        fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        with(binding) {

            swipeRefresh.setOnRefreshListener { fetchData() }

            btnTry.setOnClickListener {
                viewModel.getAnimals(etQuery.text.toString())
            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    private fun fetchData() {
        viewModel.getSelectedAnimals()
    }

    private fun initLiveData() {
        viewModel.loading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.swipeRefresh.isEnabled = false
                    binding.swipeRefresh.isRefreshing = true
                } else {
                    binding.swipeRefresh.isEnabled = true
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }

        viewModel.animals2.observe(viewLifecycleOwner) {
            it?.let { adapter.submitData(lifecycle, it) }
        }

    }


}