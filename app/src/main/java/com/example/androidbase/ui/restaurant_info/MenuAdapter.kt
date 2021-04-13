package com.example.androidbase.ui.restaurant_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.GlideRequests
import com.example.androidbase.databinding.ItemMenuBinding
import com.example.domain.model.Menu
import com.example.domain.model.PopularItem

class MenuAdapter(
    private val glide: GlideRequests
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var menu: MutableList<Menu> = mutableListOf()

    fun updateMenuItems(list: List<Menu>) {
        if (list.isNotEmpty()) {
            menu.clear()
        }

        menu.addAll(list)
        notifyDataSetChanged()
    }

    // TODO: not all menus have popularItems, plug in later.
    private val popularItems: List<PopularItem>
        get() = menu.firstOrNull()?.popular_items ?: emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuViewHolder(ItemMenuBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menu[position])
    }

    override fun getItemCount(): Int {
        return menu.size
    }

    inner class MenuViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popularItem: PopularItem) {
            with(binding) {
                businessName.text = popularItem.name
                description.text = popularItem.description
                price.text = popularItem.price.toString()
                glide.load(popularItem.img_url)
                    .fitCenter()
                    .placeholder(android.R.drawable.ic_menu_camera)
                    .into(foodImageView)
            }
        }

        fun bind(menu: Menu) {
            with(binding) {
                businessName.text = menu.name
                description.text = menu.subtitle
            }
        }
    }
}