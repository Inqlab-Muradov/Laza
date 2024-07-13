package com.inqlab.laza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inqlab.laza.databinding.ImageRvBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val imageList = ArrayList<String>()
    inner class ImageViewHolder(val binding : ImageRvBinding)  :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = ImageRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList[position]
        holder.binding.image = item
    }

    fun updateList(newList : List<String>){
        imageList.clear()
        imageList.addAll(newList)
        notifyDataSetChanged()
    }
}