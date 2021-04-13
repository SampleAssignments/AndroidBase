package com.example.androidbase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.GlideRequests
import com.example.androidbase.databinding.ItemRestaurantBinding
import com.example.domain.model.Restaurant

class StoreFeedAdapter(
    private val glide: GlideRequests,
    private val onRestaurantClickListener: OnRestaurantClickListener
) : PagingDataAdapter<Restaurant, StoreFeedAdapter.RestaurantItemViewHolder>(RESTAURANT_COMPARATOR) {

    interface OnRestaurantClickListener {
        fun onRestaurantSelected(restaurant: Restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        val binding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        getItem(position)?.let { restaurant ->
            holder.bind(restaurant)
        }
    }

    inner class RestaurantItemViewHolder(
        private val binding: ItemRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onRestaurantClickListener.onRestaurantSelected(it)
                }
            }
        }

        fun bind(restaurant: Restaurant) {
            with(binding) {
                businessName.text = restaurant.name
                description.text = restaurant.description
                if (restaurant.distance_from_consumer >= 0.0) {
                    distance.text = restaurant.distance_from_consumer.toString()
                }

                glide.load(restaurant.cover_img_url)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_camera)
                    .into(imageView)
            }
        }
    }

    companion object {
        val RESTAURANT_COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}