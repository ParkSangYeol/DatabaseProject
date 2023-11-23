package com.team11.database.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.AppDatabase
import com.team11.database.R
import com.team11.database.View.FoodAdapter
import com.team11.database.View.FoodInfoAdapter


class FoodInfoFragment : Fragment() {
    private var adapter: FoodInfoAdapter? = null
    private lateinit var context: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 데이터셋
        val fname = requireArguments().getString("Fname")
        if (fname != null) {
            val database = AppDatabase.getDatabase(context)
            val ingredientDataset = database.IngredientDao().findIngredientByFname(fname)


            // 가져온 데이터 셋 확인
            Log.d("[FoodInfoFragment]", "ingredientDataset size: " + ingredientDataset.size)
            var i = 0
            for (foodData in ingredientDataset) {
                Log.d("[FoodInfoFragment]", "index: " + i++ + "ingredientData: " + ingredientDataset.size)
            }

            // 어댑터 초기화
            adapter = FoodInfoAdapter(ingredientDataset.toTypedArray(), requireContext())

            // RecyclerView 설정
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_Ingredient)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            // 데이터 가져오기
            val poisonInfo = database.FPDao().getPoisonInfoByFname(fname)

            // 잠복기 설정
            val incubationPeriodView: TextView = view.findViewById<TextView>(R.id.textView_incubation_period)
            incubationPeriodView.text = "최소 " + poisonInfo.Min_IP + "일에서 최대 " + poisonInfo.Max_IP + "일"
            
            // 사멸온도 설정
            val deathCondition: TextView = view.findViewById(R.id.textView_death_conditions)
            deathCondition.text = poisonInfo.MaxTemperature.toString() + "°C 이상에서 " + poisonInfo.MaxTime + " 분 이상 가열시 사멸"
        }
        else {
            Log.e("[FoodInfoFragment]", "Fname is null in Bundle")
        }
    }
}