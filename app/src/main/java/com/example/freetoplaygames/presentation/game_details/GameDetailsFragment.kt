package com.example.freetoplaygames.presentation.game_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.freetoplaygames.R
import com.example.freetoplaygames.databinding.FragmentGameDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {
    private val viewModel : GameDetailsViewModel by viewModels()
    lateinit var binding: FragmentGameDetailsBinding
    val args : GameDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGameDetails(args.gameId)
        viewModel.state.observe(viewLifecycleOwner, Observer {
            it.gameDetails?.let {game->
                binding.imgGame.load(game.thumbnail){
                    placeholder(R.drawable.joker)
                }
                binding.tvTitleDetails.text=game.title
                binding.tvDescriptionDetails.text=game.description
                binding.tvGenreDetails.text=game.genre
                binding.tvOs.text=game.os
                binding.tvProcessor.text=game.processor
                binding.tvMemory.text=game.memory
                binding.tvGraphics.text=game.graphics
                binding.tvStorage.text=game.storage
                binding.btnMovePage.setOnClickListener {
                    val webPage = Uri.parse(game.gameUrl)
                    val webIntent = Intent(Intent.ACTION_VIEW,webPage)
                    startActivity(webIntent)
                }
                binding.btnAnnuler.setOnClickListener {
                    view.findNavController().popBackStack()
                }
                binding.progressBarForDetails.visibility=View.GONE
                binding.detailsView.visibility=View.VISIBLE
            }
            if(it.isLoading){
                binding.progressBarForDetails.visibility=View.VISIBLE
                binding.detailsView.visibility=View.GONE
            }

            if(it.error.isNotEmpty()){
                binding.tvInfoDetails.text=it.error
                binding.tvInfoDetails.visibility=View.VISIBLE
                binding.progressBarForDetails.visibility=View.GONE
                binding.detailsView.visibility=View.GONE
                binding.imgLoadDetails.visibility=View.VISIBLE
                binding.imgLoadDetails.setOnClickListener {
                    view.findNavController().popBackStack()
                }
            }
        })
    }
}