package com.inqlab.laza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inqlab.laza.ViewModel.RegisterViewModel
import com.inqlab.laza.base.BaseFragment
import com.inqlab.laza.databinding.FragmentRegisterBinding
import com.inqlab.laza.utils.gone
import com.inqlab.laza.utils.visible


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.signUp.setOnClickListener {
            register()
        }
        binding.backRegister.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToStartedFragment())
        }

    }

    private fun observeData(){
        viewModel.isLoading.observe(viewLifecycleOwner){
            if (it) binding.loadingRegister.visible() else binding.loadingRegister.gone()
        }

        viewModel.isSuccess.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(this.context,"Register is Successful",Toast.LENGTH_SHORT).show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMainActivity2())
            } else {
                viewModel.errorMessage.observe(viewLifecycleOwner){
                    Toast.makeText(this.context,it,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun register(){

        val email = binding.emailText.text.toString().trim()
        val password = binding.passwordText.text.toString().trim()
        val username = binding.usernameText.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()){
            viewModel.register(email, password)
        }else{
            Toast.makeText(this.context,"Enter email and password",Toast.LENGTH_SHORT).show()
        }

    }

}