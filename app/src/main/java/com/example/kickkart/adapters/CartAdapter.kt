package com.example.kickkart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.kickkart.databinding.CartItemBinding
import com.example.kickkart.helper.ManagementCart
import com.example.kickkart.models.ItemsModel
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartAdapter(val context: Context, val list: MutableList<ItemsModel>, var changeNumberItemsListener: ChangeNumberItemsListener? = null) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private val managementCart = ManagementCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$${item.price}"
        holder.binding.totalPriceTxt.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberTxt.text = item.numberInCart.toString()

        Glide.with(context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.itemImg)

        holder.binding.plusBtn.setOnClickListener {
            managementCart.plusItem(list as ArrayList<ItemsModel>, position, object: ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

        holder.binding.minusBtn.setOnClickListener {
            managementCart.minusItem(list as ArrayList<ItemsModel>, position, object: ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
    }

    class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}