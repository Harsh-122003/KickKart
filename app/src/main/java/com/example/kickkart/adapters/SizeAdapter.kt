package com.example.kickkart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kickkart.R
import com.example.kickkart.databinding.SizeItemBinding

class SizeAdapter(private val context: Context, private val items: ArrayList<String>) : RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
    private var selectedPosition: Int = -1
    private var lastSelectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SizeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.sizeTxt.text = items[position]

        holder.binding.sizeLayout.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition == holder.adapterPosition)
        {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_background_selected)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purple))
        }
        else
        {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_background)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    inner class ViewHolder(val binding: SizeItemBinding) : RecyclerView.ViewHolder(binding.root){
        
    }
}