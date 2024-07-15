package com.example.kickkart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kickkart.models.BrandModel
import com.example.kickkart.models.ItemsModel
import com.example.kickkart.models.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel(){
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val banner = MutableLiveData<MutableList<SliderModel>>()
    private val brand = MutableLiveData<MutableList<BrandModel>>()
    private val recommendation = MutableLiveData<MutableList<ItemsModel>>()

    val banners: LiveData<MutableList<SliderModel>> = banner
    val brands: LiveData<MutableList<BrandModel>> = brand
    val recommendations: LiveData<MutableList<ItemsModel>> = recommendation

    fun loadBanners(){
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children)
                {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if(list != null)
                    {
                        lists.add(list)
                    }
                }
                banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadBrands(){
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for(childSnapshot in snapshot.children)
                {
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if(list != null)
                    {
                        lists.add(list)
                    }
                }
                brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadRecommendations(){
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children)
                {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if(list != null)
                    {
                        lists.add(list)
                    }
                }
                recommendation.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}