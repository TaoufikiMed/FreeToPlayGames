package com.example.freetoplaygames.presentation.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ProgressBar
import androidx.core.animation.doOnEnd
import androidx.navigation.findNavController
import com.example.freetoplaygames.R
import com.example.freetoplaygames.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding : FragmentSplashBinding
    private lateinit var progressBar: ProgressBar
    private lateinit var anim: Animator
    val currentProgress=100
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSplashBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar=binding.progressBar
        anim = ObjectAnimator.ofInt(progressBar,"progress",currentProgress)
        anim.setDuration(2000)
            .doOnEnd {
                view.findNavController().navigate(R.id.action_splashFragment_to_gameListFragment)
            }
        anim.start()
    }
}