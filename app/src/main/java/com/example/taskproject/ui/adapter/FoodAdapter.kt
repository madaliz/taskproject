package com.example.taskproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite


class FoodAdapter(private var foodList: MutableList<Food>) :
    ListAdapter<Food, FoodAdapter.FoodViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun updateFoods(newFoods: List<Food>) {
        submitList(newFoods)
    }
    fun addFood(food: Food) {
        val updatedList = currentList.toMutableList()
        updatedList.add(food)
        submitList(updatedList)
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val iconFavorite : ImageView = itemView.findViewById(R.id.img_favorite)

        fun bind(food: Food) {
            foodName.text = food.name
            iconFavorite.visibility = View.GONE

            }


    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.foodId == newItem.foodId
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }
}
