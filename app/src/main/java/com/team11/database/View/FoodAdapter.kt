package com.team11.database.View

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.AppDatabase
import com.team11.database.Data.Food
import com.team11.database.R


class FoodAdapter(private val foodDataset: Array<Food>, private val context: Context) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: CardView = view.findViewById(R.id.content)
        val foodIngredient: RecyclerView = view.findViewById(R.id.recycler_ingredient_button)
        val foodName: TextView = view.findViewById(R.id.text_food_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_food, parent, false)
        val holder:FoodViewHolder = FoodViewHolder(content)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.foodIngredient.setLayoutManager(layoutManager)

        holder.foodIngredient

        return holder
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        // 색상 설정

        // 텍스트 설정
        holder.foodName.text = foodDataset[position].Fname
        val ingredientList = AppDatabase.getDatabase(context).IngredientDao()
            .findIngredientNameByFnumber(foodDataset[position].Fnumber)
        holder.foodIngredient.adapter = ButtonAdapter(ingredientList.toTypedArray(), context)

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