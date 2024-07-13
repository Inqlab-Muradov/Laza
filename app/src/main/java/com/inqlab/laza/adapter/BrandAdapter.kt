package com.inqlab.laza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inqlab.laza.databinding.ItemBrandBinding
import com.inqlab.laza.model.Brand

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private val brandList = ArrayList<Brand>()

    inner class BrandViewHolder(val binding : ItemBrandBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = ItemBrandBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BrandViewHolder(view)
    }

    override fun getItemCount(): Int {
       return brandList.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
       val item = brandList[position]
        holder.binding.brandItem = item
        holder.binding.brandImage.setImageResource(item.image)
    }

    fun updateList(newList:List<Brand>){
        brandList.clear()
        brandList.addAll(newList)
        notifyDataSetChanged()
    }
}