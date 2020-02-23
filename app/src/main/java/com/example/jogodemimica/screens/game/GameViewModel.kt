package com.example.jogodemimica.screens.game
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel()
{

  /** List of words to be used */
  val listOfWords = mutableListOf("Banana","Mobile","Phone")

  /**Live Data*/
  //the word to be shown
  val _word = MutableLiveData<String>()
  val word: LiveData<String>
    get() = _word

  // The score to be shown
  val _score = MutableLiveData<Int>()
  val score: LiveData<Int>
    get() = _score

  //is game finished
  val _isGameOver = MutableLiveData<Boolean>()
  val isGameOver : LiveData<Boolean>
    get() = _isGameOver

  init
  {
    _score.value = 0
    _word.value = "Almeida"
    _isGameOver.value = false
  }

  /** Button Listeners */
  fun onCorrect()
  {
    nextWord()
    _score.value = _score.value?.plus(1)
  }

  fun onSkip()
  {
    nextWord()
    _score.value = _score.value?.minus(1)
  }

  /** Randomizes the words*/
  fun nextWord()
  {
    if(listOfWords.isEmpty())
    {
      _isGameOver.value = true
    }
    else
    {
      _word.value = listOfWords.removeAt(0)
      listOfWords.shuffle()
    }

  }

}


