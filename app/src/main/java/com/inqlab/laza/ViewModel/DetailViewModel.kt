package com.inqlab.laza.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inqlab.laza.api.CommentService
import com.inqlab.laza.api.ProductService
import com.inqlab.laza.model.Comment
import com.inqlab.laza.model.ProductResponseItem
import com.inqlab.laza.repository.NetworkResponse
import com.inqlab.laza.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val repository: Repository
)
    : ViewModel() {

    val product = MutableLiveData<ProductResponseItem>()
    val isLoading = MutableLiveData<Boolean>()
    val sizeList = ArrayList<String>()
    val commentList = MutableLiveData<List<Comment>>()
    val errorMessage = MutableLiveData<String>()

    val observableSizeList = MutableLiveData<ArrayList<String>>()

    fun getProductDetail(id:String){
        isLoading.value = true
        viewModelScope.launch {
            val response = repository.getProductDetails(id)
            when (response){
                is NetworkResponse.Success->{
                    response.data?.let {
                        product.value = it
                        isLoading.value = false
                    }
                }
                is NetworkResponse.Error->{
                    response.message?.let {
                        errorMessage.value = it
                        isLoading.value = false
                    }
                }
            }
        }
    }

    fun setSizeData(){

        sizeList.add("XS")
        sizeList.add("S")
        sizeList.add("M")
        sizeList.add("L")
        sizeList.add("2Xl")
        sizeList.add("3Xl")
        sizeList.add("4XL")

        observableSizeList.value = sizeList

    }

    fun getAllComment(){
        viewModelScope.launch {
            val response = repository.getAllComments()
            when (response){
                is NetworkResponse.Success->{
                    response.data?.comments?.let {
                        commentList.value = it
                    }
                }
                is NetworkResponse.Error->{
                    response.message?.let {
                        errorMessage.value = it
                    }
                }
            }
        }
    }

}