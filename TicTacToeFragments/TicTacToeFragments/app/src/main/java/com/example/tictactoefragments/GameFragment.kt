package com.example.tictactoefragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class GameFragment : Fragment() {

    private lateinit var grid: List<Button>
    private lateinit var gridContainer: LinearLayout
    private lateinit var chooseButton: Button
    val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        grid = listOf(
            view.findViewById(R.id.cell_1),
            view.findViewById(R.id.cell_2),
            view.findViewById(R.id.cell_3),
            view.findViewById(R.id.cell_4),
            view.findViewById(R.id.cell_5),
            view.findViewById(R.id.cell_6),
            view.findViewById(R.id.cell_7),
            view.findViewById(R.id.cell_8),
            view.findViewById(R.id.cell_9)
        )

        savedInstanceState?.getIntArray("MOVES")
            ?.let { it ->
                viewModel.moves.clear()
                viewModel.moves.addAll(it.toList())
            }

        savedInstanceState?.getCharArray("PLAYERS")
            ?.let { it ->
                viewModel.player.clear()
                viewModel.player.addAll(it.toList())
            }

        gridContainer = view.findViewById(R.id.grid_container)
        chooseButton = view.findViewById(R.id.choose_button)

        chooseButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_gameFragment_to_chooseFragment))

        for (i in grid.indices) {
            val g = grid[i]
            g.setOnClickListener {
                if (!viewModel.end && viewModel.move(i)) {
                    if (viewModel.end) {
                        endMessage()
                    }
                    drawBoard()
                }
            }
        }

        drawBoard()

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("MOVES", viewModel.moves.toIntArray())
        outState.putCharArray("PLAYERS", viewModel.player.toCharArray())
    }

    private fun newGame() {
        viewModel.newGame()
        drawBoard()
    }

    private fun drawBoard() {
        grid.forEach { it.text = "" }
        viewModel.moves.forEachIndexed { index, i ->
            grid[i].text = viewModel.player[index % 2].toString()
        }
        chooseButton.isEnabled = viewModel.freshGame
    }

    private fun endMessage() {
        when (viewModel.winner) {
            true -> view?.findNavController()?.navigate(R.id.action_gameFragment_to_loseFragment)
            false -> view?.findNavController()?.navigate(R.id.action_gameFragment_to_winFragment)
            else -> Toast.makeText(context, "It's a draw!", Toast.LENGTH_LONG).show()
        }
    }
}
