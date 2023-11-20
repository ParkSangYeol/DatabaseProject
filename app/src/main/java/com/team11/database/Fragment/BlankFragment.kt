package com.team11.database.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.team11.database.R


class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val textView2 = view.findViewById<TextView>(R.id.textView2)
        val textView3 = view.findViewById<TextView>(R.id.textView3)
        textView2.text = requireArguments().getString("Fname")
        textView3.text = requireArguments().getInt("Fnumber").toString()
        // Inflate the layout for this fragment
        return view
    }

}