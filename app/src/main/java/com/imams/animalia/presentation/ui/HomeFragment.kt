package com.imams.animalia.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imams.animalia.databinding.FragmentHomeBinding
import com.imams.animalia.presentation.adapter.GroupAnimalAdapter
import com.imams.animalia.presentation.disabled
import com.imams.animalia.presentation.enabled
import com.imams.animalia.presentation.gone
import com.imams.animalia.presentation.viewmodel.HomeViewModel
import com.imams.animalia.presentation.visible
import com.imams.animals.mapper.ModelMapper.toJson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var job: Job? = null

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
                etQuery.setQuery(null, false)
                fetchData()
                swipeRefresh.isRefreshing = false
            }

            etQuery.setOnQueryTextListener(object : OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchAnimals(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false

            })

            etQuery.setOnCloseListener { etQuery.setQuery(null, false)
                true
            }

            btnSort.setOnClickListener {
                // todo
            }

            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    private fun fetchData() {
        adapter.refresh()
        viewModel.getAnimals()
    }

    private fun initLiveData() {
        viewModel.loading.observe(viewLifecycleOwner) {
            it?.let {
                showSkeleton(it)
                binding.swipeRefresh.isEnabled = !it
            }
        }

        viewModel.animalsSearchResult.observe(viewLifecycleOwner) {
            it?.let {
                adapter.refresh()
                adapter.submitData(lifecycle, it)
                binding.btnSort.disabled()
            }
        }

        viewModel.animals2.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitData(lifecycle, it)
                binding.btnSort.enabled()
            }
        }

    }

    private fun showSkeleton(show: Boolean) {
        with(binding) {
            if (show) {
                shimmerSkeleton.visible()
                swipeRefresh.gone()
            } else {
                shimmerSkeleton.gone()
                swipeRefresh.visible()
            }
        }
    }

    private fun searchAnimals(query: String?) {
        job?.cancel()
        job = lifecycleScope.launch {
            if (query == null) {
                viewModel.getAnimals()
                return@launch
            }
            delay(500)
            viewModel.getAnimals(query)
        }
        job?.start()
    }

}