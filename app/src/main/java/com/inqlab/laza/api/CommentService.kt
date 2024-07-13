package com.inqlab.laza.api


import com.inqlab.laza.model.CommentResponse
import retrofit2.http.GET

interface CommentService {

    @GET("comments")
    suspend fun getAllComments() : retrofit2.Response<CommentResponse>
}