package com.imams.animalia.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imams.animalia.R
import com.imams.animalia.databinding.ItemAnimalBinding
import com.imams.animals.model.Animal

class AnimalAdapter(
    private var list: List<Animal>,
    private val callback: (Animal) -> Unit,
): RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submit(list: List<Animal>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAnimalBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Animal) {
            with(binding) {
                tvName.text = item.name
                tvLocation.text = "${item.locations.take(3)}"
                tvTaxonomies.text = item.taxonomy.family
            }
            itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAnimalBinding.bind(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean =
                oldItem == newItem

        }
    }

}