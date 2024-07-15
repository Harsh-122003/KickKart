package com.example.kickkart.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kickkart.R
import com.example.kickkart.models.BrandModel

class BrandAdapter(private val items: List<BrandModel>,private val context: Context): RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.brand_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.brandName.text = item.title

        Glide.with(context)
            .load(item.picUrl)
            .into(holder.brandImage)

        holder.lLayout.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.brandName.setTextColor(context.resources.getColor(R.color.white))

        if(selectedPosition == position)
        {
            holder.brandImage.setBackgroundResource(0)
            holder.lLayout.setBackgroundResource(R.drawable.purple_background)
            ImageViewCompat.setImageTintList(holder.brandImage, ColorStateList.valueOf(context.resources.getColor(R.color.white)))
            holder.brandName.visibility = View.VISIBLE
        }
        else
        {
            holder.brandImage.setBackgroundResource(R.drawable.grey_background)
            holder.lLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.brandImage, ColorStateList.valueOf(context.resources.getColor(R.color.black)))
            holder.brandName.visibility = View.GONE
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val lLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        val brandImage: ImageView = itemView.findViewById(R.id.brandImage)
        val brandName: TextView = itemView.findViewById(R.id.brandName)
    }
}