package com.inqlab.laza.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.inqlab.laza.base.BaseFragment
import com.inqlab.laza.databinding.FragmentFirstScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirstScreenFragment : BaseFragment<FragmentFirstScreenBinding>(FragmentFirstScreenBinding::inflate) {

    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.skip.setOnClickListener {
            findNavController().navigate(FirstScreenFragmentDirections.actionFirstScreenFragmentToStartedFragment())
        }

        getSharedPreferences()
    }

    private fun getSharedPreferences(){
        val login =sharedPreferences.getBoolean("isLogin",false)
        if (login) {
            findNavController().navigate(FirstScreenFragmentDirections.actionFirstScreenFragmentToMainActivity2())
        }
    }
}