package com.livmas.air_tikets.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.livmas.air_tikets.databinding.FragmentTicketsBinding

class TicketsFragment : Fragment() {
    private lateinit var viewModel: TicketsViewModel
    private lateinit var binding: FragmentTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }
}