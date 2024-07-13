package com.inqlab.laza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.inqlab.laza.base.BaseFragment
import com.inqlab.laza.databinding.FragmentStartedBinding


class StartedFragment : BaseFragment<FragmentStartedBinding>(FragmentStartedBinding::inflate) {


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.backStarted.setOnClickListener {
         findNavController().navigate(StartedFragmentDirections.actionStartedFragmentToFirstScreenFragment())
      }

      binding.createAccount.setOnClickListener {
         findNavController().navigate(StartedFragmentDirections.actionStartedFragmentToRegisterFragment())
      }

      binding.signIn.setOnClickListener {
         findNavController().navigate(StartedFragmentDirections.actionStartedFragmentToLoginFragment())
      }
   }


}