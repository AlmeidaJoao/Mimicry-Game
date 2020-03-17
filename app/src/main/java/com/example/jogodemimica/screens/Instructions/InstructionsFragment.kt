package com.example.jogodemimica.screens.Instructions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.jogodemimica.R
import com.example.jogodemimica.databinding.InstructionsFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class InstructionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: InstructionsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)
        return binding.root
    }
}
