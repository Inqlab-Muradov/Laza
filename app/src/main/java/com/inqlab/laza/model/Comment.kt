package com.inqlab.laza.model


import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("postId")
    val postId: Int?,
    @SerializedName("user")
    val user: User?
)