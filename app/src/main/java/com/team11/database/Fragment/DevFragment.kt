package com.team11.database.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.team11.database.MainActivity
import com.team11.database.R

class DevFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextInputLayout 가져오기
        val queryInputField: TextInputLayout = view.findViewById(R.id.textInputLayout_SQL_Query)

        // 테이블 보기 버튼 설정
        val showTableButton: Button = view.findViewById(R.id.button_showTable)
        showTableButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_devFragment_to_devShowTableFragment)
        }
        
        // 결과 출력 버튼 설정
        val printResultButton: Button = view.findViewById(R.id.button_printResult)
        printResultButton.setOnClickListener{
            val bundle = bundleOf(
                "SQL Query" to queryInputField.editText!!.text.toString()
            )
            view.findNavController().navigate(R.id.action_devFragment_to_devResultFragment, bundle)
        }
    }
}