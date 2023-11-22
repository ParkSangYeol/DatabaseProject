package com.team11.database.View
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.Food_poisoning
import com.team11.database.Data.Ingredient
import com.team11.database.R

class IngredientInfoAdapter (private val fpDataset: Array<Food_poisoning>) :
    RecyclerView.Adapter<IngredientInfoAdapter.IngredientInfoViewHolder>() {

    class IngredientInfoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ingredientInfoContent: LinearLayout = view.findViewById(R.id.ingredient_info_content)
        val ingredientInfoName: TextView = view.findViewById(R.id.ingredient_info_name)
        val ingredientInfoTempTime: TextView = view.findViewById(R.id.ingredient_info_temp_time)
        val ingredientInfoIp: TextView = view.findViewById(R.id.ingredient_info_ip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientInfoViewHolder {
        val ingredientContent = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_ingredient_info, parent, false)
        return IngredientInfoViewHolder(ingredientContent)
    }

    override fun onBindViewHolder(holder: IngredientInfoViewHolder, position: Int) {

        holder.ingredientInfoName.text = fpDataset[position].CAname
        holder.ingredientInfoTempTime.text = getTempTimeStr(fpDataset[position].Temperature.toString(), fpDataset[position].Time.toString())
        holder.ingredientInfoIp.text = getIp(fpDataset[position].Min_IP.toString(), fpDataset[position].Max_IP.toString())
    }
    override fun getItemCount() = fpDataset.size

    fun getTempTimeStr(temp: String, time: String): String
    {
        return "사멸온도 : 섭씨 $temp 도, 가열시간: $time 분"
    }

    fun getIp(minIp: String, maxIp: String): String
    {
        return "$minIp 시간 ~ $maxIp 시간"
    }

}