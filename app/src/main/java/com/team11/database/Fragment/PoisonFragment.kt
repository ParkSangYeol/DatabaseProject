package com.team11.database.Fragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.FoodAdapter
import com.team11.database.View.PoisonAdapter

class PoisonFragment : Fragment(){
    private var adapter: PoisonAdapter? = null
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
        return inflater.inflate(R.layout.fragment_poison, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = mainActivity.getDatabase()

        // 데이터셋
        val poisonDataset = db.FPDao().findFpDiedOnTemp(55)

        // 어댑터 초기화
        adapter = PoisonAdapter(poisonDataset.toTypedArray(), requireContext())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.poison_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val textView1 = view.findViewById<TextView>(R.id.seek_temp)
        val textView2 = view.findViewById<TextView>(R.id.seek_temp)
        val seekBar = view.findViewById<SeekBar>(R.id.poison_seek)
        seekBar.min = 55
        seekBar.max = 125


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textView1.text = "섭씨 ${p1.toString()}도 에서 \n최소 표기 시간만큼 가열"
                val resultSet = db.FPDao().findFpDiedOnTemp(p1)
                adapter = PoisonAdapter(resultSet.toTypedArray(), requireContext())
                recyclerView.adapter = adapter
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                println("움직임 시작")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                println("움직임 끝")

            }

        })


    }
}