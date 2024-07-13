package com.inqlab.laza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inqlab.laza.databinding.ImageDetailBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    private val imageList = ArrayList<String>()

inner class ImageViewHolder(val binding : ImageDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = ImageDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
         return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList[position]
        holder.binding.image = item

    }

    fun updateImageList(newList :List<String> ){
        imageList.clear()
        imageList.addAll(newList)
        notifyDataSetChanged()
    }

}