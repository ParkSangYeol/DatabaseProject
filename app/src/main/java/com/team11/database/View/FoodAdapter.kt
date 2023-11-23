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
import com.team11.database.Data.Food
import com.team11.database.R

class FoodAdapter(private val foodDataset: Array<Food>, private val context: Context) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: LinearLayout = view.findViewById(R.id.content)
        val colorView: View = view.findViewById(R.id.view_food_color)
        val foodIngredient: TextView = view.findViewById(R.id.text_food_ingredient)
        val foodName: TextView = view.findViewById(R.id.text_food_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_food, parent, false)
        val red = (Math.random() * 256).toInt()
        val green = (Math.random() * 256).toInt()
        val blue = (Math.random() * 256).toInt()

        val randomColor = Color.rgb(red, green, blue)
        val holder:FoodViewHolder = FoodViewHolder(content)
        holder.colorView.setBackgroundColor(randomColor)

        return holder
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        // 색상 설정

        // 텍스트 설정
        holder.foodName.text = foodDataset[position].Fname
        val ingredientList = AppDatabase.getDatabase(context).IngredientDao()
            .findIngredientNameByFnumber(foodDataset[position].Fnumber)
        Log.d("[FoodAdapter]", "ingredientList Size = " + ingredientList.size)

        val ingredient = StringBuilder()
        if (ingredientList.isNotEmpty()) {
            ingredient.append(ingredientList[0])
            for (index in 1 until ingredientList.size) {
                ingredient.append(", ")
                ingredient.append(ingredientList[index])
                Log.d("[FoodAdapter]", "ingredientList add = " + ingredientList[index])
            }
            Log.d("[FoodAdapter]", "ingredients = " + ingredient.toString())
        }

        Log.d("[FoodAdapter]", "ingredients = " + ingredient)

        holder.foodIngredient.text = ingredient.toString()

        holder.content.setOnClickListener {
            // 버튼 클릭시 처리를 진행하는 부분
            // TODO 버튼 클릭시 팝업되는 fragment를 구현한 뒤, 내부 변수를 설정하고, 해당 fragment를 보여주는 방식으로 구현
            Log.d(
                "[FoodAdapter]", "content clicked. foodNum = " + foodDataset[position].Fnumber +
                        "foodName = " + foodDataset[position].Fname
            )
            val bundle = bundleOf(
                "Fnumber" to foodDataset[position].Fnumber,
                "Fname" to foodDataset[position].Fname
            )
            holder.view.findNavController()
                .navigate(R.id.action_foodFragment_to_foodInfoFragment, bundle)
        }
    }

    override fun getItemCount() = foodDataset.size
}