package com.inqlab.laza.ViewModel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val sharedPreferences : SharedPreferences
) : ViewModel() {

    val auth = FirebaseAuth.getInstance()
    val isLogin = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val loginPref  = MutableLiveData<Boolean>()

    fun login(email : String,password : String) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLogin",true)
                editor.apply()
                isLogin.value = true
            }else{
                isLogin.value = false
            }
            isLoading.value = false
        }
    }

}