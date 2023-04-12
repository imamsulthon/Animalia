package com.imams.animalia.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.imams.animalia.databinding.FragmentFavoriteBinding
import com.imams.animalia.presentation.adapter.AnimalAdapter
import com.imams.animalia.presentation.viewmodel.FavoriteViewModel
import com.imams.animals.mapper.ModelMapper.toJson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment: Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        AnimalAdapter(listOf(), callback = {
            requireActivity().startActivity(Intent(requireActivity(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.TAG, it.toJson())
            })
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSelectedAnimals()
    }

    private fun fetchData() {
        viewModel.getSelectedAnimals()
    }

    private fun initLiveData() {
        viewModel.animals.observe(viewLifecycleOwner) {
            it?.let { adapter.submit(it) }
        }
    }

}