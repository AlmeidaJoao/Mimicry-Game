package com.example.jogodemimica.screens.game


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.jogodemimica.R
import com.example.jogodemimica.databinding.GameFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment()
{

  private lateinit var binding:GameFragmentBinding
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View?
  {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater,R.layout.game_fragment,container,false)

    return binding.root
  }


}
