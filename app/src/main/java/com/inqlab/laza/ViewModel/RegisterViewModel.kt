package com.inqlab.laza.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    val auth = FirebaseAuth.getInstance()
    val isSuccess = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun register(email : String,password : String){
        isLoading.value = true
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                isSuccess.value = true

            }else{
               isSuccess.value = false
                errorMessage.value = it.exception?.localizedMessage.toString()
            }
            isLoading.value = false
        }

    }
}