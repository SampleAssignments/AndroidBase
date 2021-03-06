package com.example.androidbase.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbase.GlideApp
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentHomeBinding
import com.example.androidbase.ui.BaseFragment
import com.example.domain.model.Location
import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding // this is only valid between onViewCreated and OnCreateView
        get() = _binding!!

    private lateinit var storeFeedAdapter: StoreFeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        initAdapter()

        binding.swipeRefresh.setOnRefreshListener { storeFeedAdapter.refresh() }

        lifecycleScope.launchWhenCreated {
            storeFeedAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun initAdapter() {
        val glide = GlideApp.with(this)
        storeFeedAdapter =
            StoreFeedAdapter(glide, object : StoreFeedAdapter.OnRestaurantClickListener {
                override fun onRestaurantSelected(restaurant: Restaurant) {
                    Timber.d("selected restaurant is $restaurant")
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(
                            restaurant.id.toString()
                        )
                    )
                }
            })
        binding.restaurantList.adapter = storeFeedAdapter
        binding.restaurantList.layoutManager = LinearLayoutManager(context)

        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.restaurantList.addItemDecoration(decoration)

        storeFeedAdapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && storeFeedAdapter.itemCount == 0
            showEmptyList(isListEmpty)

            // Only show the list if refresh succeeds.
            binding.restaurantList.isVisible = loadState.source.refresh is LoadState.NotLoading

            // Display error message on the UI.
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error

            errorState?.let {
                binding.infoTextView.text = it.error.localizedMessage
                binding.infoTextView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @ExperimentalCoroutinesApi
    override fun subscribeToViewModel() {
        loadData()
    }

    private fun showEmptyList(isEmpty: Boolean) {
        if (isEmpty) {
            binding.infoTextView.text = getString(R.string.generic_no_results)
            binding.infoTextView.visibility = View.VISIBLE
            binding.swipeRefresh.visibility = View.GONE
        } else {
            binding.infoTextView.visibility = View.GONE
            binding.swipeRefresh.visibility = View.VISIBLE
        }
    }

    private fun loadData() {
        viewModel.getRestaurantFeed(Location(37.422740, -122.139956)).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Error -> {
                    binding.infoTextView.visibility = View.VISIBLE
                    binding.swipeRefresh.visibility = View.GONE
                    binding.infoTextView.text = it.throwable.localizedMessage
                }
                Result.Loading -> {
                    binding.infoTextView.visibility = View.VISIBLE
                    binding.swipeRefresh.visibility = View.GONE
                    binding.infoTextView.text = getString(R.string.generic_loading)
                }
                is Result.Success -> {
                    binding.swipeRefresh.visibility = View.VISIBLE
                    binding.infoTextView.visibility = View.GONE
                    storeFeedAdapter.submitData(lifecycle, it.data)
                }
            }
        }
    }
}