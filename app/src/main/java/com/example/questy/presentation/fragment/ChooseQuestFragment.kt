package com.example.questy.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.questy.R
import com.example.questy.databinding.FragmentChooseQuestBinding
import com.example.questy.domain.model.Quest
import com.example.questy.presentation.rcv.QuestAdapter
import com.example.questy.presentation.viewmodel.MainViewModel
import com.example.questy.presentation.viewmodel.MainViewModelFactory

class ChooseQuestFragment : Fragment() {

    private val vm by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]
    }
    private var _binding: FragmentChooseQuestBinding? = null
    private val binding: FragmentChooseQuestBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseQuestBinding == null")

    private lateinit var questAdapter: QuestAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseQuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupQuestList()
        initAdapter(view.context)
        setupQuestItem()
//        btnNavigationMenu()
    }

//    private fun btnNavigationMenu() {
//        with(binding) {
//            btnNavMenu.selectedItemId = R.id.homeQuest
//            btnNavMenu.setOnItemSelectedListener {
//                TODO() // Сделать переходы
//                true
//            }
//        }
//    }

    private fun launchBookingQuestFragment(quest: Quest) {
        findNavController().navigate(ChooseQuestFragmentDirections.
        actionChooseQuestFragmentToBookingAQuestFragment(quest))
    }

    private fun setupQuestList() {
        vm.questList.observe(viewLifecycleOwner) {
            questAdapter.submitList(it)
        }
    }

    private fun setupQuestItem() {
        questAdapter.onQuestItemClickListener = {
            launchBookingQuestFragment(it)
        }
    }

    private fun initAdapter(context: Context) {
        with(binding.rcvQuest) {
            layoutManager = GridLayoutManager(context, 1)
            questAdapter = QuestAdapter()
            adapter = questAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}