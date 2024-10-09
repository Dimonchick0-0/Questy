package com.example.questy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.questy.R
import com.example.questy.databinding.FragmentBookingAQuestBinding
import com.example.questy.databinding.FragmentUserBookingQuestBinding

class UserBookingQuestFragment : Fragment() {

    private val args by navArgs<UserBookingQuestFragmentArgs>()

    private var _binding: FragmentUserBookingQuestBinding? = null
    private val binding: FragmentUserBookingQuestBinding
        get() = _binding ?: throw RuntimeException("FragmentBookingAQuestBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBookingQuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnNav()
    }

    private fun setupBtnNav() = with(binding) {
        btnNavMenu.selectedItemId = R.id.booking
        btnNavMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeQuest -> launchChooseQuestFragment()
            }
            true
        }
    }

    private fun launchChooseQuestFragment() {
        findNavController().navigate(
            UserBookingQuestFragmentDirections.actionUserBookingQuestFragmentToChooseQuestFragment()
        )
    }
}