package com.scan.e_commerce.view

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.scan.base.view.BaseFragment
import com.scan.e_commerce.R
import com.scan.e_commerce.databinding.FragmentProductListBinding
import com.scan.e_commerce.presentation.ProductUiState
import com.scan.e_commerce.presentation.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedStateViewModel


class ProductListFragment : BaseFragment<FragmentProductListBinding,ProductUiState,ProductViewModel>() {
    override fun getVM(): ProductViewModel = getSharedStateViewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_product_list

    override fun renderState(uiState: ProductUiState) {
        when(uiState) {
            is ProductUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.productsRecyclerView.adapter = ProductRecyclerAdapter(uiState.products) {
                    findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment)
                    viewModel.openProductDetails(it)
                }
            }
            is ProductUiState.Error -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(),uiState.failureMessage,Toast.LENGTH_LONG).show()
            }
            is ProductUiState.Loading -> binding.progressBar.visibility = View.VISIBLE

        }
    }
}