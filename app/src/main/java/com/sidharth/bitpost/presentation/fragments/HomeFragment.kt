package com.sidharth.bitpost.presentation.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sidharth.bitpost.R
import com.sidharth.bitpost.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        setupViewsAndListeners()
        return fragmentHomeBinding.root
    }

    private fun setupViewsAndListeners() {
        fragmentHomeBinding.etPurpose.movementMethod = ScrollingMovementMethod()
        fragmentHomeBinding.bottomBar.btnGenerate.setOnClickListener {
            if (isInputValid()) {
                val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(getPrompt())
                findNavController().navigate(action)
            }
        }
    }

    private fun getPrompt(): String {
        return "Persona: ${getStyle()} " +
                "platform: ${getPlatform()} " +
                "purpose: ${fragmentHomeBinding.etPurpose.text} " +
                "Formality: ${getFormality()} " +
                "Tone: ${getTone()} " +
                "Length: ${getLength()} "
    }

    private fun isInputValid(): Boolean {
        return fragmentHomeBinding.etPurpose.text.isNullOrBlank().not()
    }

    private fun getPlatform(): String {
        return when (fragmentHomeBinding.cgPlatform.checkedChipId) {
            R.id.chip_linkedin -> "LinkedIn"
            R.id.chip_twitter -> "Twitter"
            R.id.chip_facebook -> "Facebook"
            R.id.chip_instagram -> "Instagram"
            R.id.chip_threads -> "Threads"
            else -> "Other"
        }
    }

    private fun getFormality(): String {
        return when (fragmentHomeBinding.cgFormality.checkedChipId) {
            R.id.chip_casual -> "Casual"
            R.id.chip_neutral -> "Neutral"
            R.id.chip_formal -> "Formal"
            else -> "Required"
        }
    }

    private fun getTone(): String {
        return when (fragmentHomeBinding.cgTone.checkedChipId) {
            R.id.chip_optimistic -> "Optimistic"
            R.id.chip_worried -> "Worried"
            R.id.chip_friendly -> "Friendly"
            R.id.chip_curious -> "Curious"
            R.id.chip_assertive -> "Assertive"
            R.id.chip_encouraging -> "Encouraging"
            R.id.chip_surprised -> "Surprised"
            R.id.chip_engaging -> "Engaging"
            R.id.chip_cooperative -> "Cooperative"
            else -> "Required"
        }
    }

    private fun getLength(): String {
        return when (fragmentHomeBinding.cgLength.checkedChipId) {
            R.id.chip_short -> "Short"
            R.id.chip_medium -> "Medium"
            R.id.chip_long -> "Long"
            else -> "Required"
        }
    }

    private fun getStyle(): String {
        return when (fragmentHomeBinding.cgStyle.checkedChipId) {
            R.id.chip_none -> "Content Writer"
            R.id.chip_poetic -> "Poetic"
            R.id.chip_philosopher -> "Philosopher"
            R.id.chip_chef -> "Chef"
            R.id.chip_explorer -> "Explorer"
            R.id.chip_scientist -> "Scientist"
            R.id.chip_comedian -> "Comedian"
            R.id.chip_futurist -> "Futurist"
            else -> "required"
        }
    }

}