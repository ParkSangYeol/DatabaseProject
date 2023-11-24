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
import com.team11.database.Data.Food_poisoning
import com.team11.database.R

class PoisonAdapter(private val poisonDataset: Array<Food_poisoning>, private val context: Context) :
    RecyclerView.Adapter<PoisonAdapter.PoisonViewHolder>() {

    class PoisonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: LinearLayout = view.findViewById(R.id.poison_content)
        val colorView: View = view.findViewById(R.id.view_poison_color)
        val poisonName: TextView = view.findViewById(R.id.poison_name)
        val poisonTemp: TextView = view.findViewById(R.id.poison_temp)
        val poisonTime: TextView = view.findViewById(R.id.poison_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoisonViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_poison, parent, false)
        val red = (Math.random() * 256).toInt()
        val green = (Math.random() * 256).toInt()
        val blue = (Math.random() * 256).toInt()

        val randomColor = Color.rgb(red, green, blue)
        val holder:PoisonViewHolder = PoisonViewHolder(content)
        holder.colorView.setBackgroundColor(randomColor)

        return holder
    }

    override fun onBindViewHolder(holder: PoisonViewHolder, position: Int) {
        // 색상 설정

        // 텍스트 설정
        holder.poisonName.text = poisonDataset[position].CAname
        holder.poisonTemp.text = "${poisonDataset[position].Temperature.toString()}도"
        holder.poisonTime.text = "${poisonDataset[position].Time.toString()}초"


        holder.content.setOnClickListener {
            // 버튼 클릭시 처리를 진행하는 부분
            // TODO 버튼 클릭시 팝업되는 fragment를 구현한 뒤, 내부 변수를 설정하고, 해당 fragment를 보여주는 방식으로 구현

            val bundle = bundleOf(
                "FPnum" to poisonDataset[position].FPnumber
            )

            holder.view.findNavController()
                .navigate(R.id.action_poisonFragment_to_poisonInfoFragment, bundle)
        }
    }

    override fun getItemCount() = poisonDataset.size
}