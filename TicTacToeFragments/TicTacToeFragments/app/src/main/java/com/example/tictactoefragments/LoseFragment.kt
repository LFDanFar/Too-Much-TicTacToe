package com.example.tictactoefragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_win.*

class LoseFragment : Fragment() {

    private lateinit var shareButton: Button
    val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lose, container, false)

        shareButton = view.findViewById(R.id.share_button)
        val endMessage = view.findViewById<TextView>(R.id.end_string)
        endMessage.text = getString(R.string.win_message, viewModel.player[1])

        shareButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, viewModel.historyString())
            }.also { intent ->
                val chooserIntent =
                    Intent.createChooser(
                        intent,
                        "Share ${when (viewModel.winner) {
                            false -> "${viewModel.player[0]}'s"
                            true -> "${viewModel.player[1]}'s"
                            null -> "draw"
                        }} game!"
                    )
                startActivity(chooserIntent)
            }
        }
        return view
    }
}
