package com.example.kickkart.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.kickkart.activities.DetailActivity
import com.example.kickkart.databinding.RecommendationItemBinding
import com.example.kickkart.models.ItemsModel

class RecommendationsAdapter(private val recommendationItems: MutableList<ItemsModel>, private val context: Context) : RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecommendationItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = recommendationItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.priceTxt.text = "$" + recommendationItems[position].price.toString()
        holder.binding.titleTxt.text = recommendationItems[position].title
        holder.binding.ratingTxt.text = recommendationItems[position].rating.toString()

        val requestOptions = RequestOptions().transform(CenterInside())

        Glide.with(context)
            .load(recommendationItems[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.recommendationImage)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", recommendationItems[position])
            context.startActivity(intent)
        }
    }

    class ViewHolder(val binding: RecommendationItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}