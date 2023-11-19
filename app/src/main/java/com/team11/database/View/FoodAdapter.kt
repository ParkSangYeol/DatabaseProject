package com.team11.database.View
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.*
import com.team11.database.R

class FoodAdapter (private val foodDataset: Array<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

        class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val content: LinearLayout = view.findViewById(R.id.content)
            val foodNum: TextView = view.findViewById(R.id.food_num)
            val foodName: TextView = view.findViewById(R.id.food_name)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            val content = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_content, parent, false)
            return FoodViewHolder(content)
        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            holder.foodNum.text = foodDataset[position].Fnumber.toString()
            holder.foodName.text = foodDataset[position].Fname
            holder.content.setOnClickListener {
                // 버튼 클릭시 처리를 진행하는 부분
                // TODO 버튼 클릭시 팝업되는 fragment를 구현한 뒤, 내부 변수를 설정하고, 해당 fragment를 보여주는 방식으로 구현
                Log.d("[FoodAdapter]", "content clicked. foodNum = " + foodDataset[position].Fnumber +
                "foodName = " + foodDataset[position].Fname)
                Navigation.findNavController(holder.view).navigate(R.id.action_homeFragment_to_blankFragment)
            }
        }

        override fun getItemCount() = foodDataset.size
}