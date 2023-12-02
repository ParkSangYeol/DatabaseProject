package com.team11.database.Fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.util.newStringBuilder
import com.team11.database.Data.AppDatabase
import com.team11.database.R
import org.w3c.dom.Text


class PoisonInfoFragment : Fragment() {
    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poison_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fpnum = requireArguments().getInt("FPNumber")
        val numOfSick = requireArguments().getInt("NumOfSick")

        // 필요한 데이터 가져오기
        val database = AppDatabase.getDatabase(context)
        val foodPoisoning = database.FPDao().findFpByFPNum(fpnum.toString())
        val symptoms = database.SymptomDao().findSymptomByFPnum(fpnum)
        val diseases = database.RDDao().findRDNameByFPnum(fpnum)

        // 데이터 설정
        // 병원균 이름 설정
        val poisonName: TextView = view.findViewById(R.id.button_poisonInfo_name)
        poisonName.text = foodPoisoning.CAname

        // TODO 설명 설정하기. 기반은 완성되어 있으나 데이터 베이스에 해당 부분 누락
        // val poisonDescription: TextView = view.findViewById(R.id.text_bacteria_description)
        // poisonDescription.text = ????

        // 저번 달 발생 건수 설정
        val lastMonthText: TextView = view.findViewById(R.id.text_lastMonth)
        lastMonthText.text = ": " + numOfSick + "건"
        
        // SeekBar 설정
        val seekBar: SeekBar = view.findViewById(R.id.seekBar)
        seekBar.progress = foodPoisoning.Temperature
        seekBar.setEnabled(false)

        // 온도 텍스트 설정
        val tempText: TextView = view.findViewById(R.id.text_poisonInfo_temp)
        val temp = "" + foodPoisoning.Temperature + "도"
        val spannableString =
            SpannableString(temp + "는 " + foodPoisoning.CAname + "(으)로 부터 안전한 온도입니다.")

        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            temp.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tempText.text = spannableString

        // 증상 설정
        val symptomsText: TextView = view.findViewById(R.id.text_poisonInfo_symptom)
        val symptomString = StringBuilder()
        if (symptoms.size > 0) {
            symptomString.append(symptoms[0].Sname)
            for (i in (1..symptoms.size)) {
                symptomString.append(", " + symptoms[i].Sname)
            }
        }
        symptomsText.text = symptomString.toString()

        // 질병 설정
        val diseaseText: TextView = view.findViewById(R.id.text_poisonInfo_disease)
        val diseaseString = StringBuilder()
        if (diseases.size > 0) {
            diseaseString.append(diseases[0])
            for (i in (1..diseases.size)) {
                symptomString.append(", " + diseases[i])
            }
        }
        diseaseText.text = diseaseString.toString()

        // TODO 예방법 설정하기. 기반은 완성되어 있으나 데이터 베이스에 해당 부분 누락
        // val preventText: TextView = view.findViewById(R.id.text_poisonInfo_preventive)
        // preventText.text = ????

    }
}