package com.inqlab.laza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.inqlab.laza.ViewModel.DetailViewModel
import com.inqlab.laza.adapter.CommentAdapter
import com.inqlab.laza.adapter.ImageAdapter
import com.inqlab.laza.adapter.SizeAdapter
import com.inqlab.laza.adapter.ViewPagerAdapter
import com.inqlab.laza.base.BaseFragment
import com.inqlab.laza.databinding.FragmentDetailProductBinding
import com.inqlab.laza.utils.gone
import com.inqlab.laza.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProductFragment : BaseFragment<FragmentDetailProductBinding>(FragmentDetailProductBinding::inflate) {

   private val args : DetailProductFragmentArgs by navArgs()
   private val imageAdapter = ViewPagerAdapter()
   private val viewModel by viewModels<DetailViewModel> ()
   private val imageRvAdapter = ImageAdapter()
   private val sizeAdapter = SizeAdapter()
   private val commentAdapter = CommentAdapter()

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.viewPager.adapter = imageAdapter
      viewModel.getProductDetail(args.id)
      observeData()
      binding.backImage.setOnClickListener {
         findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToHomeFragment())
      }
      binding.imageRv.adapter = imageRvAdapter
      binding.sizeRV.adapter = sizeAdapter
      viewModel.setSizeData()
      binding.dotsIndicator.attachTo(
         binding.viewPager
      )
      binding.reviewRV.adapter = commentAdapter
      viewModel.getAllComment()
   }

   private fun observeData(){
      viewModel.product.observe(viewLifecycleOwner){
         imageAdapter.updateImageList(it.images)
         imageRvAdapter.updateList(it.images)
         binding.priceProduct.text = "${it.price}$"
         binding.description.text = it.description
         binding.brandProduct.text = it.category.name
         binding.titleProduct.text = it.title
         binding.totalPrice.text = "${it.price+5}$"
      }
      viewModel.observableSizeList.observe(viewLifecycleOwner){
         sizeAdapter.updateList(it)
      }
      viewModel.isLoading.observe(viewLifecycleOwner){
         if (it) binding.loadingDetail.visible() else binding.loadingDetail.gone()
      }
      viewModel.commentList.observe(viewLifecycleOwner){
         commentAdapter.updateList(it)
      }
      viewModel.errorMessage.observe(viewLifecycleOwner){
         Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
      }
   }


}