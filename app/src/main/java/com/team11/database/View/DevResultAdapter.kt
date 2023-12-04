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

class DevResultAdapter(private val resultDataset: Array<String>, private val context: Context) :
    RecyclerView.Adapter<DevResultAdapter.DevResultViewHolder>() {

    class DevResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.textView_devResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevResultViewHolder {
        val content = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dev_result, parent, false)
        return DevResultViewHolder(content)
    }

    override fun onBindViewHolder(holder: DevResultViewHolder, position: Int) {
        // 텍스트 설정
        holder.content.text = resultDataset[position]
    }

    override fun getItemCount() = resultDataset.size
}