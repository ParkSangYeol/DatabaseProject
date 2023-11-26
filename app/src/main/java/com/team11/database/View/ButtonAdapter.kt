package com.team11.database.View

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.AppDatabase
import com.team11.database.Data.Food
import com.team11.database.R

class ButtonAdapter(private val ingDataset: Array<String>, private val context: Context) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: Button = view.findViewById(R.id.button_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_button, parent, false)
        return ButtonViewHolder(content)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        // 색상 설정

        // 텍스트 설정
        holder.content.text = ingDataset[position]
        holder.content.setOnClickListener {
            // 버튼 클릭시 처리를 진행하는 부분
            // TODO 버튼 클릭시 팝업되는 fragment를 구현한 뒤, 내부 변수를 설정하고, 해당 fragment를 보여주는 방식으로 구현

        }
    }

    override fun getItemCount() = ingDataset.size
}