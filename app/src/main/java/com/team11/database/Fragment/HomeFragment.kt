package com.team11.database.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.FoodAdapter

class HomeFragment : Fragment(){
    private var adapter: FoodAdapter? = null
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
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = mainActivity.getDatabase()

//        val food1 = Food(1, "햄버거")
//        db.FoodDao().insertFood(food1)

        // 데이터셋
        val foodDataset = db.FoodDao().getAll()

        // 어댑터 초기화
        adapter = FoodAdapter(foodDataset.toTypedArray())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.testRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}