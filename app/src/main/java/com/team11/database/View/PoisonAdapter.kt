package com.team11.database.View

import android.content.Context
import android.graphics.Color
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
import com.team11.database.Data.Food_poisoning
import com.team11.database.R
import java.util.Calendar

class PoisonAdapter(private val poisonDataset: Array<Food_poisoning>, private val context: Context) :
    RecyclerView.Adapter<PoisonAdapter.PoisonViewHolder>() {

    class PoisonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: CardView = view.findViewById(R.id.content_poison)
        val poisonName: TextView = view.findViewById(R.id.text_poison_name)
        val poisonTime: TextView = view.findViewById(R.id.button_poison_time)
        val poisonDiseaseList: RecyclerView = view.findViewById(R.id.recycler_poisonSearch_disease)
        val poisonNumOfSick: TextView = view.findViewById(R.id.text_poison_numOfMonth )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoisonViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_poison, parent, false)
        val holder:PoisonViewHolder = PoisonViewHolder(content)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.poisonDiseaseList.setLayoutManager(layoutManager)
        return holder
    }

    override fun onBindViewHolder(holder: PoisonViewHolder, position: Int) {
        // 텍스트 설정
        holder.poisonName.text = poisonDataset[position].CAname
        holder.poisonTime.text = "가열시간: " + poisonDataset[position].Time + "초"
        val diseaseList = AppDatabase.getDatabase(context).RDDao()
            .findRDNameByFPnum(poisonDataset[position].FPnumber)
        holder.poisonDiseaseList.adapter = ButtonAdapter(diseaseList.toTypedArray(), context)

        val calendar: Calendar = Calendar.getInstance()
        val month: Int = calendar.get(Calendar.MONTH) + 1
        val numOfSick = AppDatabase.getDatabase(context).FoMDao()
            .getMonthByFPnum(poisonDataset[position].FPnumber, month)

        holder.poisonNumOfSick.text = "이번달 발생 건수 : " + numOfSick

        holder.content.setOnClickListener {
            // 버튼 클릭시 처리를 진행하는 부분
            // TODO 버튼 클릭시 팝업되는 fragment를 구현한 뒤, 내부 변수를 설정하고, 해당 fragment를 보여주는 방식으로 구현

            val bundle = bundleOf(
                "FPNumber" to poisonDataset[position].FPnumber,
                "NumOfSick" to numOfSick
            )

            holder.view.findNavController()
                .navigate(R.id.action_poisonFragment_to_poisonInfoFragment, bundle)
        }
    }

    override fun getItemCount() = poisonDataset.size
}