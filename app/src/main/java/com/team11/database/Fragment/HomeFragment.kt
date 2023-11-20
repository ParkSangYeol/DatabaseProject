package com.team11.database.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.FoodAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button_food = view.findViewById<Button>(R.id.button_food)
        val button_ingredient = view.findViewById<Button>(R.id.button_ingredient)
        val button_poison = view.findViewById<Button>(R.id.button_poison)
        val button_dev = view.findViewById<Button>(R.id.button_dev)

        button_food.setOnClickListener()
        {
            view.findNavController().navigate(R.id.action_homeFragment_to_foodFragment)
        }

        button_ingredient.setOnClickListener()
        {
            view.findNavController().navigate(R.id.action_homeFragment_to_ingredientFragment)
        }

        button_poison.setOnClickListener()
        {
            view.findNavController().navigate(R.id.action_homeFragment_to_poisonFragment)
        }

        button_dev.setOnClickListener()
        {
            view.findNavController().navigate(R.id.action_homeFragment_to_devFragment)
        }
    }
}