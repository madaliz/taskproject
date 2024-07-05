package com.example.taskproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite
import com.example.taskproject.ui.adapter.FoodAdapter
import com.example.taskproject.ui.adapter.FoodWithFavoriteAdapter
import com.example.taskproject.ui.viewmodel.FavoriteViewModel
import com.example.taskproject.ui.viewmodel.FoodViewModel
import com.example.taskproject.ui.viewmodel.PersonViewModel

class PersonDetailFragment:Fragment(R.layout.person_detail_fragment) {

    var userId=0L
    lateinit var personNameTextView : TextView
    private val personViewModel: PersonViewModel by viewModels()
    private val foodViewModel : FoodViewModel by viewModels()
    private val favoriteViewModel : FavoriteViewModel by viewModels()
    private lateinit var foodAdapter: FoodWithFavoriteAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodListWithFavorite: MutableList<FoodWithFavorite>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userId = if(arguments?.getLong("user_id") == null ) 0 else arguments?.getLong("user_id")!!
        return super.onCreateView(inflater, container, savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personNameTextView = view.findViewById(R.id.txt_person_name)
        foodListWithFavorite = mutableListOf()
        foodAdapter = FoodWithFavoriteAdapter(favoriteViewModel,userId)
        getPersonName()
        initView(view)
    }

    private fun getPersonName(){

        personViewModel.getPersonById(userId){persons ->
            personNameTextView.text  = persons?.username!!
        }
    }
    private fun initView(view: View){
        recyclerView = view.findViewById(R.id.list_food)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = foodAdapter
        loadFoods()
    }

    private fun loadFoods() {
        foodViewModel.getFoodsWithFavorites(userId){ foods ->
            foodListWithFavorite.clear()
            foodListWithFavorite.addAll(foods)
            foodAdapter.updateFoods(foodListWithFavorite)
        }
    }

}