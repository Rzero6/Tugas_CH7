package com.example.tugasch7.topic3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasch7.databinding.ActivityRetrofitBinding
import com.example.tugasch7.databinding.AddDialogBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Adapter()

        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.adapter = adapter

        getPosts()
        binding.floatingActionButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            addPost()
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
            ApiClient.service().delPost(id!!).enqueue(object : Callback<PostResponseItem>{
                override fun onResponse(
                    call: Call<PostResponseItem>,
                    response: Response<PostResponseItem>
                ) {
                    Toast.makeText(
                        this@RetrofitActivity,
                        response.code().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    getPosts()
                }

                override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                    Toast.makeText(this@RetrofitActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        dialog.setNegativeButton("Gk") { d, _ ->
            d.dismiss()
        }
        dialog.create().show()
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
            ApiClient.service().editPost(postResponseItem.id!!,title,category,content).enqueue(object : Callback<PostResponseItem>{
                override fun onResponse(
                    call: Call<PostResponseItem>,
                    response: Response<PostResponseItem>
                ) {

                    Toast.makeText(
                        this@RetrofitActivity,
                        response.code().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    getPosts()
                }

                override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                    Toast.makeText(this@RetrofitActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun addPost() {
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
            ApiClient.service().addPost(postBody).enqueue(object : Callback<PostResponseItem> {
                override fun onResponse(
                    call: Call<PostResponseItem>,
                    response: Response<PostResponseItem>
                ) {
                    Toast.makeText(
                        this@RetrofitActivity,
                        response.code().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    getPosts()
                }

                override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                    Toast.makeText(this@RetrofitActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })
            dialog.dismiss()
        }
        dialog.show()
        dialog.setOnDismissListener {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getPosts() {
        ApiClient.service().getPost().enqueue(object : Callback<List<PostResponseItem>> {
            override fun onResponse(
                call: Call<List<PostResponseItem>>,
                response: Response<List<PostResponseItem>>
            ) {
//                Toast.makeText(this@MainActivity,response.body()?.get(0)?.title.toString(),Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
                adapter.setData(response.body()!!)
                Toast.makeText(
                    this@RetrofitActivity,
                    response.code().toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<List<PostResponseItem>>, t: Throwable) {
                Toast.makeText(this@RetrofitActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}