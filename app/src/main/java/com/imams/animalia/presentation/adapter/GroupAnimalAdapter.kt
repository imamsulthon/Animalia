package com.imams.animalia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imams.animalia.R
import com.imams.animalia.databinding.ItemGroupBinding
import com.imams.animalia.presentation.gone
import com.imams.animalia.presentation.plurals
import com.imams.animalia.presentation.visible
import com.imams.animals.model.Animal
import com.imams.animals.model.GroupAnimal

class GroupAnimalAdapter(
    private val onClickGroup: (String) -> Unit,
    private val onClickItem: (Animal) -> Unit,
): PagingDataAdapter<GroupAnimal, GroupAnimalAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GroupAnimal>() {
            override fun areItemsTheSame(oldItem: GroupAnimal, newItem: GroupAnimal): Boolean =
                oldItem.group == newItem.group


            override fun areContentsTheSame(oldItem: GroupAnimal, newItem: GroupAnimal): Boolean =
                oldItem.list == newItem.list

        }
    }

    inner class ViewHolder(private val binding: ItemGroupBinding): RecyclerView.ViewHolder(binding.root) {

        private val adapter by lazy { AnimalAdapter(listOf(), callback = { onClickItem.invoke(it) }) }

        fun bind(item: GroupAnimal) {
            with(binding) {
                tvGroupName.text = item.group.plurals()
                recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = adapter
                val maxSize = 5
                if (item.list.size > maxSize) {
                    tvSeeMore.visible()
                    val seeMore = "See More(${item.list.size})"
                    tvSeeMore.text = seeMore
                } else {
                    tvSeeMore.gone()
                }
                adapter.submit(item.list.take(maxSize))
            }
            itemView.setOnClickListener {
                onClickGroup.invoke(item.group)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGroupBinding.bind(
                LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false)))
    }

}