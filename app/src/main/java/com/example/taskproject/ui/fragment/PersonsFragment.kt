package com.example.taskproject.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.domain.model.Persons
import com.example.taskproject.ui.adapter.PersonAdapter
import com.example.taskproject.ui.viewmodel.PersonViewModel


class PersonsFragment : Fragment(R.layout.persons_fragment) {

    private val personViewModel: PersonViewModel by viewModels()
    lateinit var buttonAddPerson: Button
    lateinit var editTextPersonName: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonAdapter
    private lateinit var personList: MutableList<Persons>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personList = mutableListOf()
        personAdapter = PersonAdapter()

        initView(view)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.list_persons)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = personAdapter
        buttonAddPerson = view.findViewById(R.id.button_add_person)
        editTextPersonName = view.findViewById(R.id.edit_text_person_name)
        buttonAddPerson.setOnClickListener {
            val name = editTextPersonName.text.toString()
            if (name.isNotEmpty()) {
                var person :Persons
                personViewModel.insertPerson(Persons(0, name)).observe(viewLifecycleOwner) { id ->
                    person = Persons(id, name)
                    personAdapter.addPerson(person)
                    editTextPersonName.text.clear()
                }

            }
        }

        personAdapter.itemClickListener = { item, position ->
            clickItem(item)
        }
        loadFoods()
    }

    private fun loadFoods() {
        personViewModel.getAllPerson { person ->
            personList.clear()
            personList.addAll(person)
            personAdapter.updatePersons(personList)
        }
    }

    private fun clickItem(person: Persons) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val newFragment = PersonDetailFragment()
        val bundle = Bundle().apply {
            putLong("user_id", person.userId)
        }
        newFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container, newFragment)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }
}