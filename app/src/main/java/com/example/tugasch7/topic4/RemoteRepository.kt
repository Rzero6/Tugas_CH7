package com.example.tugasch7.topic4

import com.example.tugasch7.topic3.ApiService
import com.example.tugasch7.topic3.PostBody
import com.example.tugasch7.topic3.PostResponseItem

class RemoteRepository(private val apiService: ApiService) {
    suspend fun getPosts(
        onResult: (List<PostResponseItem>?) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = apiService.getPosts()
        if (response.isSuccessful) {
            onResult(response.body())
        } else {
            onError(response.message())
        }
    }

    suspend fun addPost(postBody: PostBody,onResult:(PostResponseItem?)-> Unit,onError: (String) -> Unit){
        val response = apiService.addPosts(postBody)
        if (response.isSuccessful){
            onResult(response.body())
        } else {
            onError(response.message())
        }
    }
    suspend fun deletePost(id: Int,onResult: (PostResponseItem?) -> Unit, onError: (String) -> Unit){
        val response = apiService.deletePosts(id)
        if (response.isSuccessful){
            onResult(response.body())
        }else {
            onError(response.message())
        }
    }
    suspend fun editPost(id: Int, title: String,categories: String,content: String, onResult: (PostResponseItem?) -> Unit, onError: (String) -> Unit){
        val response = apiService.editPosts(id,title,categories,content)
        if (response.isSuccessful){
            onResult(response.body())
        }else {
            onError(response.message())
        }
    }
}