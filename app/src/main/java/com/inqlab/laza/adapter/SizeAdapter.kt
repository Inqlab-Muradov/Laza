package com.inqlab.laza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inqlab.laza.databinding.SizeItemBinding

class SizeAdapter : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private val sizeList = ArrayList<String>()

    inner class SizeViewHolder(val binding : SizeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = SizeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SizeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sizeList.size
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
       val item = sizeList[position]
        holder.binding.size = item
    }

    fun updateList(newList : List<String>){
        sizeList.clear()
        sizeList.addAll(newList)
        notifyDataSetChanged()
    }
}