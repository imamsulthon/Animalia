package com.imams.animalia.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.imams.animalia.R
import com.imams.animalia.databinding.FragmentDetailBinding
import com.imams.animalia.presentation.viewmodel.DetailViewModel
import com.imams.animals.mapper.ModelMapper.asAnimal
import com.imams.animals.model.Animal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private var isFavorite = false
    private var animal: Animal? = null

    companion object {
        const val TAG = "Animals"
    }

    private fun getDataArgs() {
        val animalS: String? = intent?.getStringExtra(TAG)
        printLog("args $animalS")
        animalS?.let {
            animal = animalS.asAnimal()
            animal?.let { setContent(it) }
        }
    }

    private fun printLog(msg: String) {
        println("DetailFragment $msg")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initLiveData()
        getDataArgs()
        fetchData()
    }

    private fun initView() {
        with(binding) {
            btnFavorite.setOnClickListener {
                viewModel.setFavorite(!isFavorite, animal)
            }
        }
    }

    private fun setContent(animal: Animal) {
        viewModel.initialize(animal)
        with(binding) {
            tvName.text = animal.name
            tvLocations.text = animal.locations.toString()
            tvCharacteristic.text = animal.characteristics.toString()
            tvTaxonomy.text = animal.taxonomy.toString()
        }
    }

    private fun setAsFavorite(favorite: Boolean) = with(binding) {
        printLog("setAsFavorite $favorite")
        btnFavorite.setImageResource(
            if (favorite) R.drawable.ic_favorite_true
            else R.drawable.ic_favorite_false
        )
    }

    private fun fetchData() {
        animal?.let { viewModel.initialize(it) }
    }

    private fun initLiveData() {
        viewModel.isFavorite.observe(this) {
            it?.let {
                isFavorite = it
                setAsFavorite(isFavorite)
            }
        }
    }

}