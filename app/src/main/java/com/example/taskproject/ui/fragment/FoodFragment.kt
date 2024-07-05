package com.example.taskproject.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.domain.model.Food
import com.example.taskproject.ui.adapter.FoodAdapter
import com.example.taskproject.ui.viewmodel.FoodViewModel

class FoodFragment : Fragment(R.layout.food_fragment) {

    private val foodViewModel: FoodViewModel by viewModels()
    lateinit var buttonAddFood : Button
    lateinit var editTextFoodName : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodList: MutableList<Food>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodList = mutableListOf()
        foodAdapter = FoodAdapter(foodList)

        initView(view)

    }

    private fun initView(view: View){
        recyclerView = view.findViewById(R.id.list_food)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = foodAdapter
        buttonAddFood = view.findViewById(R.id.button_add_food)
        editTextFoodName = view.findViewById(R.id.edit_text_food_name)
        buttonAddFood.setOnClickListener {
            val name = editTextFoodName.text.toString()
            if (name.isNotEmpty()) {
                val food = Food( 0,name)
                foodViewModel.insertFood(food)
                foodAdapter.addFood(food)
                editTextFoodName.text.clear()
            }
        }
        loadFoods()
    }

    private fun loadFoods() {
        foodViewModel.getFoods { foods ->
            foodList.clear()
            foodList.addAll(foods)
            foodAdapter.updateFoods(foodList)
        }
    }
}