package com.example.jogodemimica.screens.score


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.jogodemimica.R
import com.example.jogodemimica.databinding.ScoreFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    private lateinit var binding: ScoreFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)
        binding.playAgainButton.setOnClickListener { navigate() }
        return binding.root
    }

    fun navigate()
    {
        findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
    }

}
