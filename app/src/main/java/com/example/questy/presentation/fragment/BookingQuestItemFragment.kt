package com.example.questy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.questy.R
import com.example.questy.databinding.FragmentBookingAQuestBinding
import com.example.questy.databinding.FragmentBookingQuestItemBinding

class BookingQuestItemFragment : Fragment() {

    private var _binding: FragmentBookingQuestItemBinding? = null
    private val binding: FragmentBookingQuestItemBinding
        get() = _binding ?: throw RuntimeException("FragmentBookingQuestItemBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingQuestItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}