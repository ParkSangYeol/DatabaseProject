package com.team11.database.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.Ingredient
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
        val Inumber = requireArguments().getInt("Inumber")

        // Ingredient 정보 가져오기
        val ingredient = db.IngredientDao().findIngredientByNumber(Inumber!!)

        // 데이터셋
        val fpDataset = db.FPDao().findFpByInumber(Inumber)

        // 어댑터 초기화
        adapter = IngredientInfoAdapter(fpDataset.toTypedArray(), requireContext())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.ingredient_info_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // 식재료 이름 설정
        val ingredientName: TextView = view.findViewById(R.id.textView_ingredientName)
        ingredientName.text = Iname

        // 식재료 상태 설정하기
        val ingredientCondition: TextView = view.findViewById(R.id.button_ingredient_state3)
        ingredientCondition.text = ingredient.Icondition

        // 데이터 가져오기
        val poisonInfo = db.FPDao().getPoisonInfoByInumber(Inumber!!)

        // 잠복기 설정
        val incubationPeriodView: TextView = view.findViewById<TextView>(R.id.textView_ingredient_incubation_period)
        incubationPeriodView.text = poisonInfo.Min_IP.toString() + " 에서 " + poisonInfo.Max_IP + " 시간"

        // 사멸온도 설정
        val deathCondition: TextView = view.findViewById(R.id.textView_ingredient_death_conditions)
        deathCondition.text = poisonInfo.MaxTemperature.toString() + "°C 이상에서\n" + poisonInfo.MaxTime + " 초 이상 가열"

        // 찾은 식재료 수 설정
        val ingredientNum: TextView = view.findViewById(R.id.textView_numOfPoison)
        ingredientNum.text = fpDataset.size.toString() + " Poison found"
    }
}