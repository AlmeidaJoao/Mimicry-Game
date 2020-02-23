package com.example.jogodemimica.screens.game
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel()
{

  val listOfWords = listOf("Banana","Mobile","Phone")
  /**Live Data*/
  //the word to be shown
  val _word = MutableLiveData<String>()
  val word: LiveData<String>
    get() = _word

  // The score to be shown
  val _score = MutableLiveData<Int>()
  val score: LiveData<Int>
    get() = _score

  init
  {
    _score.value = 0
    _word.value = "Almeida"
  }

  fun onNextWord()
  {
  }

}


