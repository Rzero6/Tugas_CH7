package com.example.tugasch7.topic3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasch7.databinding.PostItemBinding

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var data= mutableListOf<PostResponseItem>()
    private lateinit var onDeleteClick:(Int?) ->Unit
    private lateinit var onEditClick:(PostResponseItem) -> Unit
    fun setOnDeleteClick(onClick: (Int?) -> Unit) {
        this.onDeleteClick = onClick
    }
    fun setOnEditClick(onClick: (PostResponseItem)-> Unit){
        this.onEditClick=onClick
    }
    fun setData(posts: List<PostResponseItem>){
        data.clear()
        data.addAll(posts)
        notifyDataSetChanged()
    }
    inner class ViewHolder(var binding: PostItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindViewHolder(postResponseItem: PostResponseItem){
            binding.textViewTitle.text= postResponseItem.title
            binding.textViewCategory.text= postResponseItem.categories
            binding.textViewContent.text= postResponseItem.content
            binding.textView5.text=postResponseItem.id.toString()
            binding.imageView3.setOnClickListener {
                onDeleteClick(postResponseItem.id)
            }
            binding.imageView.setOnClickListener {
                onEditClick(postResponseItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding: PostItemBinding= PostItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(data[position])
    }

    override fun getItemCount(): Int = data.size
}