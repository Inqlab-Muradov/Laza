package com.inqlab.laza.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inqlab.laza.ViewModel.HomeViewModel
import com.inqlab.laza.adapter.BrandAdapter
import com.inqlab.laza.adapter.ProductAdapter
import com.inqlab.laza.base.BaseFragment
import com.inqlab.laza.databinding.FragmentHomeBinding
import com.inqlab.laza.model.ProductResponseItem
import com.inqlab.laza.utils.gone
import com.inqlab.laza.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.newFixedThreadPoolContext

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel> ()
    private val productAdapter = ProductAdapter()
    private val brandAdapter = BrandAdapter ()
    private val filteredList = ArrayList<ProductResponseItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBrandData()
        viewModel.getAllData()
        binding.productRv.adapter = productAdapter
        observeData()
        binding.brandRV.adapter = brandAdapter
        productAdapter.onClick = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToDetailProductFragment(it))
        }
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filteredList.clear()
                val text = p0!!.lowercase()
                viewModel.productList.observe(viewLifecycleOwner){
                    for(item in it){
                        if (item.title.lowercase().contains(text)){
                            filteredList.add(item)
                        }
                    }
                    if (filteredList.isEmpty()){
                        Toast.makeText(context,"No data",Toast.LENGTH_SHORT).show()
                    }else{
                        productAdapter.updateFilteredList(filteredList)
                    }
                }
                return false
            }
        })

    }

    private fun observeData(){
        viewModel.productList.observe(viewLifecycleOwner){
            productAdapter.updateList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            if (it) binding.loadingAnimation.visible()else binding.loadingAnimation.gone()
        }
        viewModel.observableBrandList.observe(viewLifecycleOwner){
            brandAdapter.updateList(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }
    }

}