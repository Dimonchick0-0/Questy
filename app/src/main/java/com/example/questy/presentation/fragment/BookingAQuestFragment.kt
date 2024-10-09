package com.example.questy.presentation.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.questy.R
import com.example.questy.databinding.FragmentBookingAQuestBinding
import com.example.questy.domain.model.Quest
import com.example.questy.domain.model.User
import com.example.questy.presentation.viewmodel.BookingMainViewModel
import com.example.questy.presentation.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class BookingAQuestFragment : Fragment() {

    private val args by navArgs<BookingAQuestFragmentArgs>()

    private val vm by lazy {
        ViewModelProvider(this)[BookingMainViewModel::class.java]
    }

    private var _binding: FragmentBookingAQuestBinding? = null
    private val binding: FragmentBookingAQuestBinding
        get() = _binding ?: throw RuntimeException("FragmentBookingAQuestBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingAQuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        bindViews()
        addTextChangeListeners()
        setupBtnNav()
        viewLifecycleOwner.lifecycleScope.launch {
            bookingData()
        }
    }

    private suspend fun bookingData() {
        binding.saveButton.setOnClickListener {
            val name = binding.etName.text.toString()
            val number = binding.etCount.text.toString()
            val date = binding.etDate.text.toString()
            vm.bookingQuestItem(name, number, date)
            if (name.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty()) {
                lifecycleScope.launch {
                    vm.userBookingAQuest(name, number.toLong(), date.toInt(),
                        args.questItem.title
                    )
                }
                lifecycleScope.launch {
                    loadBookingQuest()
                }
            }
        }
    }

    private suspend fun loadBookingQuest() = with(binding) {
        progressBookQuest.isVisible = true
        saveButton.isEnabled = false
        etName.isEnabled = false
        etCount.isEnabled = false
        etDate.isEnabled = false
        delay(5000)
        vm.questBooking.observe(viewLifecycleOwner) {
            launchUserBookingQuest(it)
        }
        progressBookQuest.isVisible = false
        saveButton.isEnabled = true
        etName.isEnabled = true
        etCount.isEnabled = true
        etDate.isEnabled = true
    }

    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.resetErrorInputData()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun setupBtnNav() {
        with(binding) {
            btnNavMenu.selectedItemId = R.id.booking
            btnNavMenu.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeQuest -> launchBtnNav()
                }
                true
            }
        }
    }

    private fun launchBtnNav() {
        findNavController().navigate(
            BookingAQuestFragmentDirections.actionBookingAQuestFragmentToChooseQuestFragment()
        )
    }

    private fun launchUserBookingQuest(user: User) {
        findNavController().navigate(
            BookingAQuestFragmentDirections.actionBookingAQuestFragmentToUserBookingQuestFragment(
                user
            )
        )
    }

    private fun bindViews() = with(binding) {
        args.questItem.image?.let { bookingImgQuest.setImageResource(it) }
        bookingTvQuestName.text = args.questItem.title
        bookingTvQuestDescr.text = args.questItem.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}