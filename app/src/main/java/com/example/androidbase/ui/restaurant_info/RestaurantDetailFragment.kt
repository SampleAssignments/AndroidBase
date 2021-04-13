package com.example.androidbase.ui.restaurant_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbase.GlideApp
import com.example.androidbase.GlideRequests
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentRestaurantDetailBinding
import com.example.androidbase.ui.BaseFragment
import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Displays the restaurant details
 */
@AndroidEntryPoint
class RestaurantDetailFragment : BaseFragment(R.layout.fragment_restaurant_detail) {

    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()

    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding: FragmentRestaurantDetailBinding
        get() = _binding!!

    private val glide: GlideRequests by lazy {
        GlideApp.with(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRestaurantDetailBinding.bind(view)

        initAdapter()
    }

    private fun initAdapter() {
        binding.menuList.adapter = MenuAdapter(glide)
        binding.menuList.layoutManager = LinearLayoutManager(context)

        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.menuList.addItemDecoration(decoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun subscribeToViewModel() {
        viewModel.getRestaurantInfo(args.businessId).observe(viewLifecycleOwner) {
            onRestaurantResult(it)
        }
    }

    // Takes care of updating the UI based on Result states
    private fun onRestaurantResult(result: Result<Restaurant>) {
        when (result) {
            is Result.Error -> {
                binding.progressBar.visibility = View.GONE
                Snackbar.make(
                    binding.root,
                    getString(R.string.generic_unable_to_load),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            Result.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Result.Success -> {
                binding.progressBar.visibility = View.GONE

                val restaurant = result.data
                binding.businessName.text = restaurant.name
                binding.address.text = restaurant.address?.printable_address
                (binding.menuList.adapter as MenuAdapter?)?.updateMenuItems(restaurant.menus)

                glide.load(restaurant.cover_img_url)
                    .fitCenter()
                    .into(binding.contentImageView)
            }
        }
    }
}