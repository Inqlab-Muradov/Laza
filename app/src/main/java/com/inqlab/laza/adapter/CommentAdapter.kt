package com.inqlab.laza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inqlab.laza.databinding.CommentItemBinding
import com.inqlab.laza.model.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>()  {

    private val commentList = ArrayList<Comment>()

    inner class CommentViewHolder(val binding:CommentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = CommentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = commentList[position]
        holder.binding.commentItem = item
    }

    fun updateList(newList : List<Comment>){
        commentList.clear()
        commentList.addAll(newList)
        notifyDataSetChanged()
    }
}