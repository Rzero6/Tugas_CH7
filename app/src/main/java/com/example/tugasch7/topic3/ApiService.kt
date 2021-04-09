package com.example.tugasch7.topic3

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/api/posts")
    fun getPost(): Call<List<PostResponseItem>>

    @GET("/api/posts")
    suspend fun getPosts(): Response<List<PostResponseItem>>

    @POST("/api/posts")
    fun addPost(@Body postBody: PostBody) : Call<PostResponseItem>

    @POST("/api/posts")
    suspend fun  addPosts(@Body postBody: PostBody) : Response<PostResponseItem>

    @DELETE("/api/posts/{id}")
    fun delPost(@Path("id") id: Int) : Call<PostResponseItem>


    @DELETE("/api/posts/{id}")
    suspend fun deletePosts(@Path("id") id : Int) : Response<PostResponseItem>

    @FormUrlEncoded
    @PUT("/api/posts/{id}")
    suspend fun editPosts(@Path("id") id: Int,
                          @Field("title") title : String,
                          @Field("categories") categories : String,
                          @Field("content") content : String)
    : Response<PostResponseItem>

    @FormUrlEncoded
    @PUT("/api/posts/{id}")
    fun editPost(@Path("id") id: Int,
                          @Field("title") title : String,
                          @Field("categories") categories : String,
                          @Field("content") content : String)
            : Call<PostResponseItem>

}