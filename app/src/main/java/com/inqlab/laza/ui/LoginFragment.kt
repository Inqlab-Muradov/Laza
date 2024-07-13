package com.inqlab.laza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inqlab.laza.R
import com.inqlab.laza.ViewModel.LoginViewModel
import com.inqlab.laza.base.BaseFragment
import com.inqlab.laza.databinding.FragmentLoginBinding
import com.inqlab.laza.utils.gone
import com.inqlab.laza.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backLogin.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToStartedFragment())
        }
        binding.login.setOnClickListener {
            login()
        }
        observeData()
    }

    private fun login(){

        val email = binding.emailLogin.text.toString().trim()
        val password = binding.passwordLogin.text.toString().trim()

        if(email.isNotEmpty() && password.isNotEmpty()){
            viewModel.login(email, password)
        }else{
            Toast.makeText(this.context,"Enter email and password",Toast.LENGTH_SHORT).show()
        }
    }

   private fun observeData(){
       viewModel.isLogin.observe(viewLifecycleOwner){
           if (it) {
               Toast.makeText(this.context,"Login is successful",Toast.LENGTH_SHORT).show()
               findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainActivity2())
           }
           else Toast.makeText(this.context,"Login is unsuccessful",Toast.LENGTH_SHORT).show()
       }
       viewModel.isLoading.observe(viewLifecycleOwner){
           if(it) binding.ladingLogin.visible() else binding.ladingLogin.gone()
       }
       viewModel.loginPref.observe(viewLifecycleOwner){
           if (it){
               findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainActivity2())
           }
       }
   }

}