package com.example.questy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.questy.R
import com.example.questy.databinding.FragmentWelcomBinding

class WelcomFragment : Fragment() {

    private var _binding: FragmentWelcomBinding? = null
    private val binding: FragmentWelcomBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChoose.setOnClickListener {
            launchChooseQuestFragment()
        }
    }

    private fun launchChooseQuestFragment() {
        findNavController().navigate(R.id.action_welcomFragment_to_chooseQuestFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}