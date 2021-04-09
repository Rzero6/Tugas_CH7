package com.example.tugasch7.topic4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasch7.topic3.PostBody
import com.example.tugasch7.topic3.PostResponseItem
import kotlinx.coroutines.launch

class MyViewModel (private val repository: RemoteRepository) : ViewModel(){
    private val _data = MutableLiveData<List<PostResponseItem>>()
    private val _error = MutableLiveData<String>()

    private val _addData = MutableLiveData<PostResponseItem>()
    val addData : LiveData<PostResponseItem> = _addData

    private val _deleteData = MutableLiveData<PostResponseItem>()
    val deleteData : LiveData<PostResponseItem> = _deleteData
    val getData: LiveData<List<PostResponseItem>> = _data

    private val _editData = MutableLiveData<PostResponseItem>()
    val editData : LiveData<PostResponseItem> = _editData
    fun getPosts(){
        viewModelScope.launch {
            repository.getPosts({
                _data.value=it
            },{
                _error.value = it
            })
        }
    }
    fun addPost(postBody: PostBody) = viewModelScope.launch {
        repository.addPost(postBody,{
            _addData.value=it
        },{
            _error.value=it
        })
    }
    fun deletePost(id : Int) = viewModelScope.launch {
        repository.deletePost(id,{
            _deleteData.value=it
        },{
            _error.value=it
        })
    }
    fun editPost(id: Int,title: String,categories: String,content: String) = viewModelScope.launch {
        repository.editPost(id,title,categories,content,{
            _editData.value=it
        },{
            _error.value=it
        })
    }
}