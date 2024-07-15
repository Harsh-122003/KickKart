package com.example.kickkart.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kickkart.adapters.ColorAdapter
import com.example.kickkart.adapters.SizeAdapter
import com.example.kickkart.adapters.SliderAdapter
import com.example.kickkart.databinding.ActivityDetailBinding
import com.example.kickkart.helper.ManagementCart
import com.example.kickkart.models.ItemsModel
import com.example.kickkart.models.SliderModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS, LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        managementCart = ManagementCart(this)
        getBundle()
        banners()
        initLists()
    }

    private fun initLists() {
        val colorList = ArrayList<String>()
        for(color in item.picUrl)
        {
            colorList.add(color)
        }

        binding.recyclerViewColor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewColor.adapter = ColorAdapter(this, colorList)

        val sizeList = ArrayList<String>()
        for(size in item.size)
        {
            sizeList.add(size)
        }

        binding.recyclerViewSize.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSize.adapter = SizeAdapter(this, sizeList)
    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for(imageUrl in item.picUrl)
        {
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.viewPagerSlider.adapter = SliderAdapter(sliderItems, this@DetailActivity, binding.viewPagerSlider)
        binding.viewPagerSlider.clipChildren = true
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.offscreenPageLimit = 1

        if(sliderItems.size > 1)
        {
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPagerSlider)
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = item.rating.toString()
        binding.descriptionTxt.text = item.description

        binding.backBtn.setOnClickListener{
            finish()
        }

        binding.addToCartBtn.setOnClickListener{
            item.numberInCart = numberOrder
            managementCart.insertFood(item)
        }

        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }
    }
}