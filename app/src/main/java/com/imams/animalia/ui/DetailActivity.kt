package com.imams.animalia.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.imams.animalia.R
import com.imams.animalia.databinding.FragmentDetailBinding
import com.imams.animalia.viewmodel.DetailViewModel
import com.imams.animals.mapper.ModelMapper.asAnimal
import com.imams.animals.model.Animal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private var isFavorite = false

    companion object {
        const val TAG = "Animals"
    }

    private fun getDataArgs() {
        val animal: String? = intent?.getStringExtra(TAG)
        printLog("args $animal")
        animal?.let { setContent(it.asAnimal()) }
    }

    private fun printLog(msg: String) {
        println("DetailFragment $msg")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        getDataArgs()
        initLiveData()
        fetchData()
    }

    private fun initView() {
        with(binding) {
            btnFavorite.setOnClickListener {
                viewModel.setFavorite(!isFavorite)
            }
        }
    }

    private fun setContent(animal: Animal) {
        with(binding) {
            tvName.text = animal.name
            tvLocations.text = animal.locations.toString()
            tvCharacteristic.text = animal.characteristics.toString()
            tvTaxonomy.text = animal.taxonomy.toString()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setAsFavorite(favorite: Boolean) = with(binding) {
        isFavorite = favorite
        btnFavorite.setImageDrawable(
            if (favorite) resources.getDrawable(R.drawable.ic_favorite_true, null)
            else resources.getDrawable(R.drawable.ic_favorite_false, null)
        )
    }

    private fun fetchData() {
        viewModel.isFavorite()
    }

    private fun initLiveData() {
        viewModel.isFavorite.observe(this) {
            it?.let { setAsFavorite(it) }
        }
    }

}