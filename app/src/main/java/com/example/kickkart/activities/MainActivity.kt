package com.example.kickkart.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.kickkart.adapters.BrandAdapter
import com.example.kickkart.adapters.RecommendationsAdapter
import com.example.kickkart.adapters.SliderAdapter
import com.example.kickkart.databinding.ActivityMainBinding
import com.example.kickkart.models.SliderModel
import com.example.kickkart.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrand()
        initRecommendations()

        window.setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS, LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
    }

    private fun initBanner() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadBanners()
        viewModel.banners.observe(this) { items ->
            banners(items)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun banners(images: MutableList<SliderModel>){
        binding.viewPagerSlider.adapter = SliderAdapter(images, this)
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)

        if(images.size > 1)
        {
            binding.dotsIndicator.attachTo(binding.viewPagerSlider)
            binding.dotsIndicator.visibility = View.VISIBLE
        }
    }

    private fun initBrand(){
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadBrands()
        viewModel.brands.observe(this) { items->
            binding.recyclerViewBrand.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewBrand.adapter = BrandAdapter(items, this@MainActivity)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun initRecommendations(){
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadRecommendations()
        viewModel.recommendations.observe(this) { items->
            binding.recyclerViewPopular.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.recyclerViewPopular.adapter = RecommendationsAdapter(items, this@MainActivity)
            binding.progressBar.visibility = View.GONE
        }
    }
}