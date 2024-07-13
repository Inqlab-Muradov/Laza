package com.inqlab.laza.repository


import com.inqlab.laza.api.CommentService
import com.inqlab.laza.api.ProductService
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    val api : ProductService,
    val commentApi : CommentService
) {
    suspend fun getAllProducts() = safeApiRequest {
        api.getAllProduct()
    }

    suspend fun getProductDetails(id:String) = safeApiRequest {
        api.getDetailProduct(id)
    }

    suspend fun getAllComments() = safeApiRequest {
        commentApi.getAllComments()
    }

    private suspend fun <T> safeApiRequest(apicall:suspend()-> Response<T>) : NetworkResponse<T> {
        try {
            val response = apicall.invoke()
            if (response.isSuccessful){
                response.body()?.let {
                    return NetworkResponse.Success(it)
                }?: return NetworkResponse.Error("Null body")
            }else{
                return NetworkResponse.Error(response.errorBody().toString())
            }

        }catch (e:Exception){
            return NetworkResponse.Error(e.localizedMessage.toString())
        }

    }
}