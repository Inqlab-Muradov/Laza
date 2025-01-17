package com.inqlab.laza.model


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("comments")
    val comments: List<Comment>?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
)