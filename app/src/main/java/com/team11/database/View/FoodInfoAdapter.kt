package com.team11.database.View

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.AppDatabase
import com.team11.database.Data.Ingredient
import com.team11.database.R

class FoodInfoAdapter(private val ingredientDataset: Array<Ingredient>, private val context: Context) :
    RecyclerView.Adapter<FoodInfoAdapter.FoodInfoViewHolder>() {

    class FoodInfoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: LinearLayout = view.findViewById(R.id.content)
        val colorView: View = view.findViewById(R.id.view_ingredient_color)
        val ingredientPoison: TextView = view.findViewById(R.id.text_ingredient_poison)
        val ingredientCondition: TextView = view.findViewById(R.id.text_ingredient_state)
        val ingredientName: TextView = view.findViewById(R.id.text_ingredient_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodInfoViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_food_info, parent, false)
        val red = (Math.random() * 256).toInt()
        val green = (Math.random() * 256).toInt()
        val blue = (Math.random() * 256).toInt()

        val randomColor = Color.rgb(red, green, blue)
        val holder:FoodInfoViewHolder = FoodInfoViewHolder(content)
        holder.colorView.setBackgroundColor(randomColor)

        return holder
    }

    override fun onBindViewHolder(holder: FoodInfoViewHolder, position: Int) {
        // 텍스트 설정
        holder.ingredientName.text = ingredientDataset[position].Iname
        holder.ingredientCondition.text = ingredientDataset[position].Icondition
        val poisonList = AppDatabase.getDatabase(context).FPDao()
            .findFpNameByIname(ingredientDataset[position].Iname)

        val poisons = StringBuilder()
        if (poisonList.isNotEmpty()) {
            poisons.append(poisonList[0])
            for (index in 1 until poisonList.size) {
                poisons.append(", ")
                poisons.append(poisonList[index])
            }
        }

        holder.ingredientPoison.text = poisons.toString()

        holder.content.setOnClickListener {
            // 버튼 클릭시 처리를 진행하는 부분
            val bundle = bundleOf("Inumber" to ingredientDataset[position].Inumber,
                "Iname" to ingredientDataset[position].Iname, "Icondition" to ingredientDataset[position].Icondition)
            holder.view.findNavController()
                .navigate(R.id.action_foodInfoFragment_to_ingredientInfoFragment, bundle)
        }
    }

    override fun getItemCount() = ingredientDataset.size
}