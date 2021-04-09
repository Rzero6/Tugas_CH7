package com.example.tugasch7.topic4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasch7.databinding.ActivityMVVMBinding
import com.example.tugasch7.databinding.AddDialogBinding
import com.example.tugasch7.topic3.ApiClient
import com.example.tugasch7.topic3.PostBody
import com.example.tugasch7.topic3.PostResponseItem

class MVVMActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMVVMBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMVVMBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = RemoteRepository(ApiClient.service())
        val adapter = MVVMAdapter()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel=ViewModelProvider(this,viewModelFactory).get(MyViewModel::class.java)
        viewModel.getPosts()
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
        viewModel.getData.observe(this,{
            adapter.setData(it)
            binding.progressBar2.visibility= View.GONE
        })
        viewModel.addData.observe(this,{
            viewModel.getPosts()
        })
        viewModel.deleteData.observe(this,{
            viewModel.getPosts()
        })
        binding.floatingActionButton2.setOnClickListener {
            showAdd()
        }
        adapter.setOnDeleteClick {
            showConfirmationDeleteDialog(it)
        }
        adapter.setOnEditClick {
            showEdit(it)
        }

    }
    private fun showConfirmationDeleteDialog(id: Int?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Hapus")
            .setMessage("Yakin mau hapus data ini?")
        dialog.setPositiveButton("Yaa") { _, _ ->
            viewModel.deletePost(id!!)
            binding.progressBar2.visibility=View.VISIBLE
        }
        dialog.setNegativeButton("Gk") { d, _ ->
            d.dismiss()
        }
        dialog.create().show()
    }
    private fun showAdd(){
        val builder = AlertDialog.Builder(this)
        val view = AddDialogBinding.inflate(layoutInflater)
        builder.setView(view.root)
        val dialog = builder.create()
        view.cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        view.saveButton.setOnClickListener {
            val title = view.etEditTitle.text.toString().trim()
            val category = view.category.text.toString().trim()
            val content = view.content.text.toString().trim()
            val postBody = PostBody(category, content, null, title)
            binding.progressBar2.visibility=View.VISIBLE
            viewModel.addPost(postBody)
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun showEdit(postResponseItem: PostResponseItem){
        val builder = AlertDialog.Builder(this)
        val view = AddDialogBinding.inflate(layoutInflater)
        builder.setView(view.root)
        val dialog = builder.create()
        view.etEditTitle.setText(postResponseItem.title)
        view.category.setText(postResponseItem.categories)
        view.content.setText(postResponseItem.content)
        view.cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        view.saveButton.setOnClickListener {
            val title = view.etEditTitle.text.toString().trim()
            val category = view.category.text.toString().trim()
            val content = view.content.text.toString().trim()
            binding.progressBar2.visibility=View.VISIBLE
            viewModel.editPost(postResponseItem.id!!,title,category,content)
            dialog.dismiss()
        }
        dialog.show()
    }
}