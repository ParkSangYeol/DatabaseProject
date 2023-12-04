package com.team11.database.Fragment

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.team11.database.Data.AppDatabase
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.DevResultAdapter
import com.team11.database.View.FoodAdapter
import com.team11.database.View.FoodInfoAdapter

class DevResultFragment : Fragment() {
    private var adapter: DevResultAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dev_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sqlQuery = requireArguments().getString("SQL Query")
        val resultText: TextView = view.findViewById(R.id.textView_devResults)

        if (sqlQuery == null) {
            // SQL Query 를 가져올 때 오류 발생.
            resultText.text = "Error Occure to get SQL Query."
            Log.e(this.javaClass.name , "SQL Query를 가져올 때 오류가 발생하였습니다.")
        }
        else if (sqlQuery.equals("")) {
            resultText.text = "SQL Query is none."
        }
        else {
            // SQL Query를 가져옴
            var query = SimpleSQLiteQuery(sqlQuery)

            // 데이터셋
            val resultList = mutableListOf<String>()

            val cursor: Cursor? = try {
                AppDatabase.getDatabase(requireContext()).DevDao().getRawQueryResult(query)
            } catch (e: Exception) {
                // RawQuery 실행 중 예외 발생 시 처리
                Log.e("DatabaseError", "Query execution failed: ${e.message}")
                resultText.text = "Error Occure to Run SQL Query."
                null
            }

            cursor?.use { c ->
                val columnCount = c.columnCount
                var index: Int = 0
                while (c.moveToNext()) {
                    val row = (0 until columnCount).joinToString(" | ") { columnIndex ->
                        c.getString(columnIndex) ?: "null"
                    }
                    resultList.add(row)
                    index++
                    Log.d(this.javaClass.name, "resultList content: " + row)
                }
                resultText.text = ""+ index + " results"
            }

            // 어댑터 초기화
            adapter = DevResultAdapter(resultList.toTypedArray(), requireContext())

            // RecyclerView 설정
            val recyclerView = view.findViewById<RecyclerView>(R.id.RecyclerView_devResult)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }

    }
}