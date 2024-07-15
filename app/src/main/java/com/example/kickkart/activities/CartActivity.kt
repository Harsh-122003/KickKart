package com.example.kickkart.activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kickkart.adapters.CartAdapter
import com.example.kickkart.databinding.ActivityCartBinding
import com.example.kickkart.helper.ManagementCart
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        binding.backBtn.setOnClickListener {
            finish()
        }

        initCartList()
        calculateCart()

        window.setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS, LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun initCartList() {
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCart.adapter = CartAdapter(this, managementCart.getListCart(), object: ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })
    }

    private fun calculateCart(){
        val percentTax = 0.02
        val delivery = 10
        val tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100.0
        val subTotal = Math.round((managementCart.getTotalFee() * 100)) / 100.0

        with(binding){
            subtotalText.text = "$${subTotal}"
            deliveryText.text = "$${delivery}"
            taxText.text = "$${tax}"
            totalText.text = "$${total}"

            emptyText.visibility = if(managementCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView.visibility = if(managementCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
            priceDialog.visibility = if(managementCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }
}