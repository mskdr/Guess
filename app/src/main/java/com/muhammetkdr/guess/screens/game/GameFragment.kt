package com.muhammetkdr.guess.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.muhammetkdr.guess.R
import com.muhammetkdr.guess.databinding.FragmentGameBinding


class GameFragment : Fragment(R.layout.fragment_game) {
    private lateinit var gameFragmentBinding: FragmentGameBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameFragmentBinding = FragmentGameBinding.inflate(layoutInflater, container, false)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
//        updateWordText()
//        updateScoreText()   her seferinde bunu yazmaktansa otomatik güncelliyoruz bu şekilde
        /*
        gameViewModel.wordLiveData.observe(viewLifecycleOwner) {
            gameFragmentBinding.wordText.text = it
        }
        gameViewModel.scoreLiveData.observe(viewLifecycleOwner) {
            gameFragmentBinding.scoreText.text = it.toString()
        }
        dataBinding ...
        bi alt satır kodu DataBinding ile bu satıra da gerek kalmıyor. Otomatik güncelleniyor*/
        gameFragmentBinding.gameViewModel = gameViewModel
        gameFragmentBinding.lifecycleOwner = viewLifecycleOwner



        gameViewModel.gameFinishEventLiveData.observe(viewLifecycleOwner) { hasGameFinished ->
            if (hasGameFinished) {
                gameFinished()
            }
        }

        /*
               gameFragmentBinding.correctButton.setOnClickListener { onCorrect() }
               gameFragmentBinding.skipButton.setOnClickListener { onSkip() }
               gameFragmentBinding.endGameButton.setOnClickListener { onEndGame() }
               dataBinding... yapıldı
               */
        return gameFragmentBinding.root
    }

    private fun onEndGame() {
        gameViewModel.onFinishGame()

    }

    //    private fun updateWordText() {
//        gameFragmentBinding.wordText.text = gameViewModel.word   live datayla birlikte gerek kalmadı
//    }
//
//    private fun updateScoreText() {
//       gameFragmentBinding.scoreText.text= gameViewModel.score.toString()  live datayla birlikte gerek kalmadı
//
//    }
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(gameViewModel.scoreLiveData.value ?: 0)
        NavHostFragment.findNavController(this).navigate(action)
        gameViewModel.disableGameFinishEvent()
    }

    private fun onSkip() {
        gameViewModel.onSkip()
//        updateScoreText()
//        updateWordText()
    }

    private fun onCorrect() {
        gameViewModel.onCorrect()
//        updateScoreText()
//        updateWordText()  live datayla birlikte gerek kalmadı
    }

}