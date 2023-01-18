package com.scan.e_commerce.view

import com.scan.base.view.BaseFragment
import com.scan.e_commerce.R
import com.scan.e_commerce.databinding.FragmentProductDetailsBinding
import com.scan.e_commerce.presentation.ProductUiState
import com.scan.e_commerce.presentation.ProductViewModel
import com.scan.e_commerce.utils.loadFileImage
import org.koin.androidx.viewmodel.ext.android.getSharedStateViewModel

class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding,ProductUiState,ProductViewModel>() {
    override fun getVM(): ProductViewModel = getSharedStateViewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_product_details

    override fun renderState(uiState: ProductUiState) {
    }

    override fun onViewAttach() {
        super.onViewAttach()
        viewModel.productModel?.let {
            binding.productDetails = it
            if (it.imageUrls.isNotEmpty()) loadFileImage(
                binding.productImageView,
                it.imageUrls[0]
            )
            binding.imageRecyclerView.adapter = ImageAdapter(it.imagesThumbnails) { imageUrl ->
                loadFileImage(binding.productImageView, imageUrl)
            }
        }
    }
}