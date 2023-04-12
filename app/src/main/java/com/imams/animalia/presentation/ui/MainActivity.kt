package com.imams.animalia.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.imams.animalia.R
import com.imams.animalia.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding) {

            val firstFragment = HomeFragment()
            val secondFragment = FavoriteFragment()

            setCurrentFragment(firstFragment)

            bottomNav.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home-> setCurrentFragment(firstFragment)
                    R.id.favorite-> setCurrentFragment(secondFragment)
                }
                true
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
        }.commit()
    }

}