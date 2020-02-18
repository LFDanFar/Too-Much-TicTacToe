package com.example.tictactoefragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController

class ChooseFragment : Fragment() {

    private lateinit var chooseText: List<EditText>
    private lateinit var okButton: Button
    private lateinit var cancelButton: Button
    val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_choose, container, false)

        chooseText = listOf(view.findViewById(R.id.choose_1), view.findViewById(R.id.choose_2))

        chooseText[0].setText(viewModel.player[0].toString())
        chooseText[1].setText(viewModel.player[1].toString())

        okButton = view.findViewById(R.id.ok_choose_button)
        cancelButton = view.findViewById(R.id.cancel_choose_button)

        okButton.isEnabled = false

        chooseText.forEach { c ->
            c.setOnClickListener { c.text.clear() }
            c.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    okButton.isEnabled = !(
                                chooseText[0].text.isEmpty() ||
                                chooseText[1].text.isEmpty() ||
                                chooseText[0].text.toString() == chooseText[1].text.toString()
                            )
                }
            })
        }

        okButton.setOnClickListener {
            viewModel.player[0] = chooseText[0].text[0]
            viewModel.player[1] = chooseText[1].text[0]
            view.findNavController().popBackStack()
        }
        cancelButton.setOnClickListener {
            view.findNavController().popBackStack()
        }

        return view
    }
}
