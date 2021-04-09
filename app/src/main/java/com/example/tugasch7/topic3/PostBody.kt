package com.example.tugasch7.topic3


import com.google.gson.annotations.SerializedName

data class PostBody(
    @SerializedName("categories")
    val categories: Any?,
    @SerializedName("content")
    val content: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: Any?
)