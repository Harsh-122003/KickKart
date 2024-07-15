package com.example.kickkart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kickkart.R

class ColorAdapter(private val context: Context, private val items: MutableList<String>) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
    private var selectedPosition: Int = -1
    private var lastSelectedPosition: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.color_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position])
            .into(holder.colorImage)

        holder.imageLayout.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition == position)
        {
            holder.imageLayout.setBackgroundResource(R.drawable.grey_background_selected)
        }
        else
        {
            holder.imageLayout.setBackgroundResource(R.drawable.grey_background)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
        val imageLayout: ConstraintLayout = itemView.findViewById(R.id.imageLayout)
        val colorImage: ImageView = itemView.findViewById(R.id.colorImage)
    }
}