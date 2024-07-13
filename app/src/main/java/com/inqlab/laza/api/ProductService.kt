package com.inqlab.laza.api

import com.inqlab.laza.model.ProductResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductService {

    @GET("products")
    suspend fun getAllProduct() : Response<ArrayList<ProductResponseItem>>

    @GET("products/{id}")
    suspend fun getDetailProduct(@Path("id") id : String) : Response<ProductResponseItem>


}