package com.imams.animalia.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.imams.animalia.databinding.FragmentHomeBinding
import com.imams.animalia.presentation.adapter.GroupAnimalAdapter
import com.imams.animalia.presentation.viewmodel.HomeViewModel
import com.imams.animals.mapper.ModelMapper.toJson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter: GroupAnimalAdapter by lazy {
        GroupAnimalAdapter(
            onClickGroup = {
                requireActivity().startActivity(
                    Intent(requireActivity(), GroupListActivity::class.java).apply {
                        putExtra(GroupListActivity.TAG, it)
                    })
            },
            onClickItem = {
                requireActivity().startActivity(
                    Intent(requireActivity(), DetailActivity::class.java).apply {
                        putExtra(DetailActivity.TAG, it.toJson())
                    })
            }
        )
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

            swipeRefresh.setOnRefreshListener {
                fetchData()
                swipeRefresh.isRefreshing = false
                etQuery.text = null
            }

            btnTry.setOnClickListener {
                viewModel.getAnimals(etQuery.text.toString())
            }

            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    private fun fetchData() {
        adapter.refresh()
        viewModel.getSelectedAnimals()
    }

    private fun initLiveData() {
        viewModel.loading.observe(viewLifecycleOwner) {
            it?.let {
                binding.swipeRefresh.isEnabled = !it
            }
        }

        viewModel.animals.observe(viewLifecycleOwner) {
            it?.let {
                adapter.refresh()
                adapter.submitData(lifecycle, it)
            }
        }

        viewModel.animals2.observe(viewLifecycleOwner) {
            it?.let { adapter.submitData(lifecycle, it) }
        }

    }

}