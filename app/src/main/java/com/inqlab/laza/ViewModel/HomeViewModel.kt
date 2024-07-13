package com.inqlab.laza.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inqlab.laza.R
import com.inqlab.laza.repository.Repository
import com.inqlab.laza.model.Brand
import com.inqlab.laza.model.ProductResponseItem
import com.inqlab.laza.repository.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository
)
    : ViewModel() {

    val productList  = MutableLiveData<List<ProductResponseItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()


    private val brandList = ArrayList<Brand>()
    val observableBrandList = MutableLiveData<List<Brand>>()

     fun getAllData(){
         isLoading.value = true
         viewModelScope.launch {
             val response = repository.getAllProducts()
             when (response){
                 is  NetworkResponse.Success->{
                     response.data?.let {
                         productList.value = it
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

    fun getBrandData(){
        brandList.add( Brand("Nike",R.drawable.nike))
        brandList.add( Brand("Fila", R.drawable.fila))
        brandList.add( Brand("Adidas", R.drawable.adidas))
        brandList.add( Brand("Puma", R.drawable.puma))

        observableBrandList.value = brandList
    }

}