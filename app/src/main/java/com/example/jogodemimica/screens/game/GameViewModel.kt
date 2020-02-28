package com.example.jogodemimica.screens.game

import android.os.Build
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

private val CORRECT_VIBRATION_PATTERN = longArrayOf(100,100)
private val TIC_TAC_PATTERN = longArrayOf(100,100,100,100)
private val NO_VIBRATION_PATTERN = longArrayOf(0)
private val GAME_OVER_PATTERN = longArrayOf(0,1898)

class GameViewModel : ViewModel()
{

  /**Enum to represent the constants*/
  enum class VibrationType(val vibrationEffect: LongArray)
  {
    NO_VIBRATION(NO_VIBRATION_PATTERN),
    TIC_TAC_VIBRATION(TIC_TAC_PATTERN),
    CORRECT_VIBRATION(CORRECT_VIBRATION_PATTERN),
    GAME_OVER_VIBRATION(GAME_OVER_PATTERN)
  }

  companion object
  {
    const val TOTAL_TIME = 60000L
    const val ONE_SECOND = 1000L
    const val LOW_TIME = 10L
    const val DONE = 0L
  }

  private val timer: CountDownTimer

  /** List of words to be used */
  val listOfWords = mutableListOf("Banana",
      "Mobile",
      "Phone",
      "Chinelo",
      "Mesa",
      "Tomada",
      "Carregador",
      "Computador",
      "Internet",
      "Mochila",
      "Cabelo",
      "Chuva",
      "Peru",
      "Extintor",
      "Relva",
      "Agua",
      "Ar Condicionado",
      "Iphone",
      "Hamburguer",
      "Teclado",
      "Mouse")

  /**Live Data*/

  val _vibrationEffect = MutableLiveData<VibrationType>()
  val vibrationEffect: LiveData<VibrationType>
    get() = _vibrationEffect

  //the word to be shown
  val _word = MutableLiveData<String>()
  val word: LiveData<String>
    get() = _word

  //when runs out time
  val _outOfTime = MutableLiveData<Boolean>()
  val outOfTime: LiveData<Boolean>
    get() = _outOfTime
  // The score to be shown
  val _score = MutableLiveData<Int>()
  val score: LiveData<Int>
    get() = _score

  //is game finished
  val _isGameOver = MutableLiveData<Boolean>()
  val isGameOver: LiveData<Boolean>
    get() = _isGameOver

  //time variable
  val _currentTime = MutableLiveData<Long>()
  val currentTime: LiveData<Long>
    get() = _currentTime


  val _currentTimeString = Transformations.map(_currentTime, { time ->
    DateUtils.formatElapsedTime(time) })

  init
  {
    _score.value = 0
    _word.value = "Almeida"
    _isGameOver.value = false
    _outOfTime.value = false

    timer = object : CountDownTimer(TOTAL_TIME, ONE_SECOND)
    {
      override fun onTick(millisUntilFinished: Long)
      {
        _currentTime.value = (millisUntilFinished / ONE_SECOND)
        if (millisUntilFinished / ONE_SECOND <= LOW_TIME)
        {
          _outOfTime.value = true
          _vibrationEffect.value = VibrationType.TIC_TAC_VIBRATION
        }
      }
      override fun onFinish()
      {
        _currentTime.value = DONE
        _vibrationEffect.value = VibrationType.GAME_OVER_VIBRATION
        _isGameOver.value = true
      }
    }
    timer.start()
  }

  /** Button Listeners */
  @RequiresApi(Build.VERSION_CODES.O)
  fun onCorrect()
  {
    _score.value = _score.value?.plus(1)
    _vibrationEffect.value = VibrationType.CORRECT_VIBRATION
    randomize()
  }

  fun onSkip()
  {
    randomize()
    _score.value = _score.value?.minus(1)
  }

  /** In case of a rematch the state of the gamefinished has to be reset*/
  fun gameDone()
  {
    _isGameOver.value = false
    _vibrationEffect.value = VibrationType.NO_VIBRATION
  }

  fun vibrationComplete()
  {
    _vibrationEffect.value = VibrationType.NO_VIBRATION
  }


  /** Randomizes the words*/
  fun randomize()
  {
    if (listOfWords.isEmpty())
    {
      _isGameOver.value = true
      _vibrationEffect.value = VibrationType.GAME_OVER_VIBRATION
    }
    else
    {
      _word.value = listOfWords.removeAt(0)
      listOfWords.shuffle()
    }
  }

}


