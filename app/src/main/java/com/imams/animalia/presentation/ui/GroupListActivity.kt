package com.imams.animalia.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imams.animalia.databinding.FragmentFavoriteBinding
import com.imams.animalia.presentation.adapter.AnimalAdapter
import com.imams.animalia.presentation.gone
import com.imams.animalia.presentation.viewmodel.GroupListViewModel
import com.imams.animalia.presentation.visible
import com.imams.animals.mapper.ModelMapper.toJson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupListActivity: AppCompatActivity() {

    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }
    private val viewModel: GroupListViewModel by viewModels()

    companion object {
        const val TAG = "Animals"
    }

    private fun printLog(msg: String) {
        println("GroupList $msg")
    }

    private val adapter by lazy {
        AnimalAdapter(listOf(), callback = {
            printLog("animal $it")
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.TAG, it.toJson())
            })
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initLiveData()
        fetchData()
    }

    private fun initView() {
        with(binding) {
            showSkeleton(true)
            swipeRefresh.setOnRefreshListener {
                fetchData()
                swipeRefresh.isRefreshing = false
            }
            recyclerView.layoutManager = LinearLayoutManager(this@GroupListActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            intent.getStringExtra(TAG)?.let {
                viewModel.getAnimals(it)
            }
        }
    }

    private fun initLiveData() {
        viewModel.loading.observe(this) {
            it?.let { showSkeleton(it) }
        }

        viewModel.animals.observe(this) {
            it?.let { adapter.submit(it) }
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

}