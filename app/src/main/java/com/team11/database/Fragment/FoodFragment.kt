package com.team11.database.Fragment
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

class FoodFragment : Fragment(){
    private var adapter: FoodAdapter? = null
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
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = mainActivity.getDatabase()

        // 데이터셋
        val foodDataset = db.FoodDao().getAll()

        // 가져온 데이터 셋 확인
        Log.d("HomeFragment", "foodDataset size: " + foodDataset.size)
        var i = 0
        for (foodData in foodDataset) {
            Log.d("HomeFragment", "index: " + i++ + "foodData: " + foodDataset.size)
        }
        
        // 어댑터 초기화
        adapter = FoodAdapter(foodDataset.toTypedArray(), requireContext())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.testRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // SearchView 설정
        val searchView = view.findViewById<SearchView>(R.id.testSearchRecyclerView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val resultSet = db.FoodDao().findFoodByName(query)
                adapter = FoodAdapter(resultSet.toTypedArray(), requireContext())
                recyclerView.adapter = adapter
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.equals(""))
                {
                    val foodDataset = db.FoodDao().getAll()
                    adapter = FoodAdapter(foodDataset.toTypedArray(), requireContext())
                    recyclerView.adapter = adapter
                }
                return true
            }
        })
        
    }
}