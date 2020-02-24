package com.example.tictactoefragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "GameListFragment"
class ListFragment : Fragment() {
    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = null

    private val GameViewModel: GameViewModel by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        gameRecyclerView = view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }
    private fun updateUI(){
        //val player = GameViewModel.player
        //adapter = GameAdapter(player)
        gameRecyclerView.adapter = adapter
    }

    companion object{
        fun newInstance(): ListFragment{
            return ListFragment()
        }
    }

    private inner class GameHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var game: GameViewModel

        private val playerBox: TextView = itemView.findViewById(R.id.winner_loser_box)
        private val turnBox: TextView = itemView.findViewById(R.id.placement_box)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(game: GameViewModel){
            this.game = game
            turnBox.text = this.game.movesPlayed
            playerBox.text = this.game.player.toString()
        }
        override fun onClick(v:View?){
            Toast.makeText(context, "${game.player}", Toast.LENGTH_SHORT).show()
        }
    }
    private inner class GameAdapter(var games: List<GameViewModel>) : RecyclerView.Adapter<GameHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.bind(game)
        }
    }
}
