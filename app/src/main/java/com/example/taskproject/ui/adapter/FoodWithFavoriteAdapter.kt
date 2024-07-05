package com.example.taskproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite
import com.example.taskproject.ui.viewmodel.FavoriteViewModel


class FoodWithFavoriteAdapter(private var favoriteViewModel : FavoriteViewModel,private val userId:Long) :
ListAdapter<FoodWithFavorite, FoodWithFavoriteAdapter.FoodWithFavoriteViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: ((item: FoodWithFavorite, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodWithFavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodWithFavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodWithFavoriteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun updateFoods(newFoods: List<FoodWithFavorite>) {
        submitList(newFoods)
    }


    inner class FoodWithFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val iconFavorite : ImageView = itemView.findViewById(R.id.img_favorite)

        fun bind(foodWithFavorite: FoodWithFavorite) {
            foodName.text = foodWithFavorite.name
            iconFavorite.visibility = View.VISIBLE
            if(foodWithFavorite.isFavorite){
                iconFavorite.setImageResource(R.drawable.icon_favorite_on)
            }else {
                iconFavorite.setImageResource(R.drawable.icon_favorite_off)
            }
            iconFavorite.setOnClickListener {
                if(!foodWithFavorite.isFavorite){
                    foodWithFavorite.isFavorite = true
                    iconFavorite.setImageResource(R.drawable.icon_favorite_on)
                    favoriteViewModel.addFavorite(userId,foodWithFavorite.foodId)
                }else {
                    foodWithFavorite.isFavorite = false
                    favoriteViewModel.removeFavorite(userId,foodWithFavorite.foodId)
                    iconFavorite.setImageResource(R.drawable.icon_favorite_off)
                }
            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodWithFavorite>() {
            override fun areItemsTheSame(oldItem: FoodWithFavorite, newItem: FoodWithFavorite): Boolean {
                return oldItem.foodId == newItem.foodId
            }

            override fun areContentsTheSame(oldItem: FoodWithFavorite, newItem: FoodWithFavorite): Boolean {
                return oldItem == newItem
            }
        }
    }
}
