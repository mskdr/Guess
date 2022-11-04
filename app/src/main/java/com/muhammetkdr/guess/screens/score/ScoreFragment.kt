package com.muhammetkdr.guess.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.guess.R
import com.muhammetkdr.guess.databinding.FragmentScoreBinding

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private lateinit var scoreFragmentBinding : FragmentScoreBinding
    private lateinit var scoreViewModel: ScoreViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scoreFragmentBinding = FragmentScoreBinding.inflate(layoutInflater, container, false)


        val scoreViewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)

        scoreViewModel = ViewModelProvider(this,scoreViewModelFactory).get(ScoreViewModel::class.java)

        scoreViewModel.onPlayAgainLiveData.observe(viewLifecycleOwner){ playAgain ->
            if(playAgain){
                goToGameScreen()
            }

        }

        scoreFragmentBinding.scoreLastText.text = scoreViewModel.score.toString()

        scoreFragmentBinding.playAgainButton.setOnClickListener {
            onPlayAgain()
        }

        return scoreFragmentBinding.root
    }

    private fun onPlayAgain() {
        scoreViewModel.onPlayAgain()
    }

    private fun goToGameScreen(){
        findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
        scoreViewModel.finishPlayAgainEvent()
    }


}