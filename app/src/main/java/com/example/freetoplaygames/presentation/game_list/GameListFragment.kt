package com.example.freetoplaygames.presentation.game_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freetoplaygames.databinding.FragmentGameListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListFragment : Fragment() {
    private val viewModel: GameListViewModel by viewModels()
    lateinit var adapter: GamesAdapter
    lateinit var binding : FragmentGameListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInputLayout.setEndIconOnClickListener {
            viewModel.getGamesByCategory(binding.textInputEditText.text.toString())
            binding.textInputEditText.setText("")
        }
        adapter=GamesAdapter()
        binding.rv.layoutManager=LinearLayoutManager(view.context)
        binding.rv.adapter=adapter
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                adapter.submitData(lifecycle,it)
                adapter.addLoadStateListener { loadState->
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            // Show progress bar
                            binding.progressBar.visibility=View.VISIBLE
                            binding.imgLoad.visibility=View.GONE
                            binding.tvInfo.visibility=View.GONE
                        }
                        is LoadState.NotLoading -> {
                            // Hide progress bar
                            binding.progressBar.visibility=View.GONE
                            binding.rv.visibility=View.VISIBLE
                        }
                        is LoadState.Error -> {
                            binding.progressBar.visibility=View.GONE
                            binding.tvInfo.text = (loadState.refresh as LoadState.Error).error.message
                            binding.tvInfo.visibility=View.VISIBLE
                            binding.rv.visibility=View.GONE
                            binding.imgLoad.setOnClickListener {
                                viewModel.getGames()
                            }
                            binding.imgLoad.visibility=View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}