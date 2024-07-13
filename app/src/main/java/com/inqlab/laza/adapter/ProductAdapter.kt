package com.inqlab.laza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inqlab.laza.databinding.ProductItemBinding
import com.inqlab.laza.model.ProductResponseItem

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList = ArrayList<ProductResponseItem>()

    lateinit var onClick : (String)->Unit

    inner class ProductViewHolder(val binding : ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.binding.productItem = item
        holder.binding.productMaterialCard.setOnClickListener {
            item.id?.let {
                onClick(it.toString())
            }
        }
        holder.binding.price.text = "${item.price}$"

    }

    fun updateList(newList : List<ProductResponseItem>){
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateFilteredList(filteredList : List<ProductResponseItem>){
        productList.clear()
        productList.addAll(filteredList)
        notifyDataSetChanged()
    }
}