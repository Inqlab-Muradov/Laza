package com.inqlab.laza.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?
)