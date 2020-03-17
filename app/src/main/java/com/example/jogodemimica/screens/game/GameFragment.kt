package com.example.jogodemimica.screens.game

import android.app.Service
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.jogodemimica.R
import com.example.jogodemimica.databinding.GameFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

  private lateinit var binding: GameFragmentBinding
  lateinit var viewModel: GameViewModel
  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

    viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

    /**
     *DataBinding
     */
    binding.gameViewModel = viewModel
    binding.lifecycleOwner = this

    /**
     *Lifecylce Observers
     */
    viewModel.isGameOver.observe(this.viewLifecycleOwner, Observer { isGameOver ->
      if (isGameOver) {
        endGame()
        viewModel.gameDone()
      }
    })

    viewModel.outOfTime.observe(this.viewLifecycleOwner, Observer { outOfTime ->
      if (outOfTime){
        binding.timerText.setTextColor(Color.RED)
      }
    })

    viewModel.vibrationEffect.observe(this.viewLifecycleOwner, Observer { vibrationEffect ->
      if(vibrationEffect != GameViewModel.VibrationType.NO_VIBRATION) {
        vibrate(vibrationEffect.vibrationEffect)
        viewModel.vibrationComplete()
      }
    })

    return binding.root
  }

  fun endGame()  {
      val finalScore = viewModel.score.value ?: 0
      findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(finalScore))
  }

  
  @RequiresApi(Build.VERSION_CODES.O)
  fun vibrate(vibe: LongArray) {
    val vibrationActivity = activity?.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
    vibrationActivity.let {
      vibrationActivity.vibrate(VibrationEffect.createWaveform(vibe,-1))
    }
  }

}
