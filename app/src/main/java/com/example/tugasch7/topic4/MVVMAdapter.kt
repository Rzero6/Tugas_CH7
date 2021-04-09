package com.example.tugasch7.topic4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasch7.databinding.PostItemBinding
import com.example.tugasch7.topic3.PostBody
import com.example.tugasch7.topic3.PostResponseItem

class MVVMAdapter : RecyclerView.Adapter<MVVMAdapter.ViewHolder>(){
    private var data = mutableListOf<PostResponseItem>()
    private lateinit var onDeleteClick:(Int?) ->Unit
    private lateinit var onEditClick:(PostResponseItem) -> Unit
    fun setOnDeleteClick(onClick: (Int?) -> Unit) {
        this.onDeleteClick = onClick
    }
    fun setOnEditClick(onClick: (PostResponseItem)-> Unit){
        this.onEditClick=onClick
    }

    fun setData(posts:List<PostResponseItem>){
        data.clear()
        data.addAll(posts)
        notifyDataSetChanged()
    }
    inner class ViewHolder(var binding: PostItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindViewHolder(getPosts:PostResponseItem){
            binding.textViewTitle.text=getPosts.title
            binding.textViewCategory.text=getPosts.categories
            binding.textViewContent.text=getPosts.content
            binding.textView5.text=getPosts.id.toString().trim()
            binding.imageView3.setOnClickListener {
                onDeleteClick(getPosts.id)
            }
            binding.imageView.setOnClickListener {
                onEditClick(getPosts)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : PostItemBinding = PostItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(data[position])
    }

    override fun getItemCount(): Int = data.size
}