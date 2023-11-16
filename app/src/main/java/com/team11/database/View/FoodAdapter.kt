package com.team11.database.View

import Food
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.R

class FoodAdapter (private val foodDataset: Array<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

        class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val foodNum: TextView = view.findViewById(R.id.food_num)
            val foodName: TextView = view.findViewById(R.id.food_name)
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): FoodViewHolder {
            val content = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_content, parent, false)
            return FoodViewHolder(content)
        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            holder.foodNum.text = foodDataset[position].Fnumber.toString()
            holder.foodName.text = foodDataset[position].Fname
        }

        override fun getItemCount() = foodDataset.size
}