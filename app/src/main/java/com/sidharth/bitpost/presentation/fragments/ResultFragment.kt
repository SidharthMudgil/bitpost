package com.sidharth.bitpost.presentation.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.sidharth.bitpost.R
import com.sidharth.bitpost.databinding.FragmentResultBinding
import com.sidharth.bitpost.domain.model.ContentResult
import com.sidharth.bitpost.presentation.viewmodel.ResultViewModel
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {

    private lateinit var fragmentResultBinding: FragmentResultBinding

    private val resultViewModel: ResultViewModel by viewModels()
    private val resultFragmentArgs: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        setupViewsAndListeners()
        observeResultData()
        fetchResult()
        return fragmentResultBinding.root
    }


    private fun fetchResult() {
        lifecycleScope.launch {
            resultViewModel.fetchResult(resultFragmentArgs.prompt)
        }
    }

    private fun observeResultData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                resultViewModel.result.collect {
                    when (it) {
                        is ContentResult.Error -> {
                            fragmentResultBinding.bottomBar.btnGenerate.isClickable = true
                            fragmentResultBinding.tvResult.text = getString(R.string.result_error)
                        }

                        is ContentResult.Loading -> {
                            fragmentResultBinding.bottomBar.btnGenerate.isClickable = false
                            fragmentResultBinding.tvResult.text = getString(R.string.typing)
                        }

                        is ContentResult.Success -> {
                            fragmentResultBinding.bottomBar.btnGenerate.isClickable = true
                            fragmentResultBinding.tvResult.text = it.content.caption
                            fragmentResultBinding.ivImage.load(it.content.image)
                        }
                    }
                }
            }
        }
    }

    private fun setupViewsAndListeners() {
        fragmentResultBinding.bottomBar.btnGenerate.text = getString(R.string.re_generate)
        fragmentResultBinding.tvResult.movementMethod = ScrollingMovementMethod()
        fragmentResultBinding.topBar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        fragmentResultBinding.bottomBar.btnGenerate.setOnClickListener { fetchResult() }
    }
}