package com.example.jogodemimica.screens.title


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.jogodemimica.R
import com.example.jogodemimica.databinding.TitleFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment()
{
    private lateinit var binding: TitleFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.title_fragment,container,false)


        binding.button.setOnClickListener {navigate()}
        return binding.root
    }

    fun navigate()
    {
        findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
    }


}
