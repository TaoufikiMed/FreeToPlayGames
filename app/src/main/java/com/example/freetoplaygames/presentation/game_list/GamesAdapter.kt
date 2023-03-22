package com.example.freetoplaygames.presentation.game_list

import android.app.FragmentHostCallback
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.freetoplaygames.R
import com.example.freetoplaygames.domain.model.Game
import com.example.freetoplaygames.presentation.game_details.GameDetailsFragmentArgs

class GamesAdapter : PagingDataAdapter<Game,GamesAdapter.MyViewHolder>(REPO_COMPARATOR){

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
                oldItem == newItem
        }
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val image = row.findViewById<ImageView>(R.id.img)
        val title = row.findViewById<TextView>(R.id.tv_title)
        val genre = row.findViewById<TextView>(R.id.tv_genre)
        val description = row.findViewById<TextView>(R.id.tv_description)
        val platform = row.findViewById<TextView>(R.id.tv_platform)
        val navButton = row.findViewById<ImageButton>(R.id.imBtnNavigate)
        val context = row.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesAdapter.MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.game_item_layout,parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: GamesAdapter.MyViewHolder, position: Int) {
        val dataAtPosition = getItem(position)!!
        holder.image.load(dataAtPosition.thumbnail){
            placeholder(R.drawable.joker)
        }
        holder.title.text=dataAtPosition.title
        holder.genre.text=dataAtPosition.genre
        holder.description.text=dataAtPosition.shortDescription
        holder.platform.text=dataAtPosition.platform
        holder.navButton.setOnClickListener {
            val webPage = Uri.parse(dataAtPosition.gameUrl)
            val webIntent = Intent(Intent.ACTION_VIEW,webPage)
            holder.context.startActivity(webIntent)
        }
        holder.row.setOnClickListener {
            //Toast.makeText(holder.context,"THE ID IS "+dataAtPosition.id,Toast.LENGTH_LONG).show()
            val action = GameListFragmentDirections.actionGameListFragmentToGameDetailsFragment(dataAtPosition.id)
            holder.row.findNavController().navigate(action)
        }
    }
}