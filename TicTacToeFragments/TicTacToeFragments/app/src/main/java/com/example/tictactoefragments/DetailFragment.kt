package com.example.tictactoefragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class DetailFragment : Fragment() {
    private lateinit var delete: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title, container, false)
        delete = view.findViewById(R.id.deleteGameButton) as Button

        delete.setOnClickListener{
            //Toast.makeText(this, "Game deleted", Toast.LENGTH_SHORT).show()
            val intent = Intent(view?.context, ListFragment::class.java)
            startActivity(intent)
        }

        return view
    }
}
