package com.team11.database.View
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.AppDatabase
import com.team11.database.Data.Food_poisoning
import com.team11.database.R
import java.util.Calendar

class IngredientInfoAdapter (private val fpDataset: Array<Food_poisoning>, private val context: Context) :
    RecyclerView.Adapter<IngredientInfoAdapter.IngredientInfoViewHolder>() {

    class IngredientInfoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ingredientInfoContent: CardView = view.findViewById(R.id.content_ingredient_poison)
        val ingredientInfoName: TextView = view.findViewById(R.id.text_ingredient_poisonName)
        val ingredientInfoDisease: RecyclerView = view.findViewById(R.id.recycler_poison_disease)
        val ingredientInfoNumOfMonth: TextView = view.findViewById(R.id.text_poison_numOfMonth)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientInfoViewHolder {
        val ingredientContent = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_ingredient_info, parent, false)
        val holder: IngredientInfoViewHolder = IngredientInfoAdapter.IngredientInfoViewHolder(ingredientContent)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.ingredientInfoDisease.setLayoutManager(layoutManager)

        return holder
    }

    override fun onBindViewHolder(holder: IngredientInfoViewHolder, position: Int) {
        holder.ingredientInfoName.text = fpDataset[position].CAname
        val diseaseList = AppDatabase.getDatabase(context).RDDao()
            .findRDNameByFPnum(fpDataset[position].FPnumber)
        holder.ingredientInfoDisease.adapter = ButtonAdapter(diseaseList.toTypedArray(), context)

        val calendar: Calendar = Calendar.getInstance()
        val month: Int = calendar.get(Calendar.MONTH)

        val numOfSick = AppDatabase.getDatabase(context).FoMDao()
            .getMonthByFPnum(fpDataset[position].FPnumber, month)

        holder.ingredientInfoNumOfMonth.text = "저번달 발생 건수 : " + numOfSick

        holder.ingredientInfoContent.setOnClickListener {
            val bundle = bundleOf(
                "FPNumber" to fpDataset[position].FPnumber,
                "NumOfSick" to numOfSick
            )
            holder.view.findNavController().navigate(R.id.action_ingredientInfoFragment_to_poisonInfoFragment, bundle)
        }
    }
    override fun getItemCount() = fpDataset.size

    fun getTempTimeStr(temp: String, time: String): String
    {
        return "사멸온도 : 섭씨 $temp 도, 가열시간: $time 초"
    }

    fun getIp(minIp: String, maxIp: String): String
    {
        return "$minIp 시간 ~ $maxIp 시간"
    }

}