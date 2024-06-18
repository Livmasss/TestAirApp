package com.livmas.air_tikets.ui.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FragmentFlightsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightsFragment : Fragment() {
    private val viewModel: FlightsViewModel by viewModel()
    private val sharedViewModel: FlightsViewModel by activityViewModel()
    private lateinit var binding: FragmentFlightsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }
}