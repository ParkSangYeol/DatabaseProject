package com.team11.database.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.IngredientInfoAdapter

class IngredientInfoFragment : Fragment() {

    private var adapter: IngredientInfoAdapter? = null
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = mainActivity.getDatabase()
        val Iname = requireArguments().getString("Iname")

        // 데이터셋
        val fpDataset = db.FPDao().findFpByIname(Iname)

        // 어댑터 초기화
        adapter = IngredientInfoAdapter(fpDataset.toTypedArray())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.ingredient_info_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}