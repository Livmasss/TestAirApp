package com.livmas.air_tikets.ui.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FragmentFlightsBinding
import com.livmas.air_tikets.ui.flights.adapter.FlightModel
import com.livmas.air_tikets.ui.flights.adapter.FlightsAdapter
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

internal class FlightsFragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val flightsAdapter = FlightsAdapter(requireContext(), listOf(
            FlightModel(0, "First", 1234, listOf(Calendar.getInstance(), Calendar.getInstance())),
            FlightModel(1, "Вторая", 10234, listOf(Calendar.getInstance())),
            FlightModel(2, "Компания", 5000, listOf()),
        ))
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val marginDecoration = VerticalMarginItemDecoration(20f)
        binding.rvFlights.apply {
            adapter = flightsAdapter
            layoutManager = manager
            addItemDecoration(dividerDecoration)
            addItemDecoration(marginDecoration)
        }
    }
}