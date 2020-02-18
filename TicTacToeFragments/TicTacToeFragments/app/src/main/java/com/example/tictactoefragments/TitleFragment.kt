package com.example.tictactoefragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController

class TitleFragment : Fragment() {

    private lateinit var playButton: Button
    val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title, container, false)

        playButton = view.findViewById(R.id.play_button)
        playButton.setOnClickListener {
            viewModel.newGame()
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
        return view
    }
}
