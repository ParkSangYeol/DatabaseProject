package com.team11.database.Fragment

import IngredientAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.FoodAdapter

class IngredientFragment : Fragment() {

    private var adapter: IngredientAdapter? = null
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = mainActivity.getDatabase()

        // 데이터셋
        val ingredientDataset = db.IngredientDao().findIngredientTriggersFp()

        // 어댑터 초기화
        adapter = IngredientAdapter(ingredientDataset.toTypedArray())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.ingredient_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // SearchView 설정
        val searchView = view.findViewById<SearchView>(R.id.ingredient_search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val resultSet = db.IngredientDao().findIngredientTriggersFpByName(query)
                adapter = IngredientAdapter(resultSet.toTypedArray())
                recyclerView.adapter = adapter
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.equals(""))
                {
                    val ingredientDataset = db.IngredientDao().findIngredientTriggersFp()
                    adapter = IngredientAdapter(ingredientDataset.toTypedArray())
                    recyclerView.adapter = adapter
                }
                return true
            }
        })

    }
}