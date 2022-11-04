package com.muhammetkdr.guess.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.guess.R
import com.muhammetkdr.guess.databinding.FragmentScoreBinding
import com.muhammetkdr.guess.databinding.FragmentTitleBinding


class TitleFragment : Fragment(R.layout.fragment_title) {

    private lateinit var titleFragmentBinding : FragmentTitleBinding
    private lateinit var titleViewModel: TitleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        titleFragmentBinding = FragmentTitleBinding.inflate(layoutInflater, container, false)

        titleFragmentBinding.playButtonViewState = PlayButtonViewState.initial()

        titleFragmentBinding.playGameButton.setOnClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }


        titleViewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        titleFragmentBinding.lifecycleOwner = viewLifecycleOwner
        titleFragmentBinding.titleViewModel = titleViewModel

        titleViewModel.playButtonVisibility.observe(viewLifecycleOwner){
            titleFragmentBinding.playButtonViewState = it
            titleFragmentBinding.executePendingBindings()
        }
        return titleFragmentBinding.root
    }

}