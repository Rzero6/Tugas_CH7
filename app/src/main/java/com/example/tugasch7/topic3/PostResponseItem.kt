package com.example.tugasch7.topic3


import com.google.gson.annotations.SerializedName

data class PostResponseItem(
    @SerializedName("categories")
    val categories: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)