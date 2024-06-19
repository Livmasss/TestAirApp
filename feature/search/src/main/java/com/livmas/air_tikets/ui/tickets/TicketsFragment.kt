package com.livmas.air_tikets.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FragmentTicketsBinding
import com.livmas.air_tikets.ui.SearchViewModel
import com.livmas.air_tikets.ui.tickets.adapter.TicketModel
import com.livmas.air_tikets.ui.tickets.adapter.TicketsRecyclerAdapter
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import com.livmas.utils.DateTimeStringifier
import java.util.Calendar

class TicketsFragment : Fragment() {
    private val viewModel: TicketsViewModel by viewModels()
    private val sharedViewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: FragmentTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onStart() {
        super.onStart()

        refreshValues()
        refreshDetails()
    }

    private fun setupViews() {
        setupRecyclerView()
        setupBackButton()
    }

    private fun refreshValues() {
        refreshCities()
        refreshDetails()
    }

    private fun setupBackButton() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun setupRecyclerView() {
        val ticketsAdapter = TicketsRecyclerAdapter(requireContext(),
            listOf(
            TicketModel(5000,
                Calendar.getInstance(), Calendar.getInstance(),
                "AER", "PRD",
                3.5f,
                false,
                "Самый удобный"
            ),
            TicketModel(5000,
                Calendar.getInstance(), Calendar.getInstance(),
                "AER", "PRD",
                3.5f,
                false,
                "Самый быстрый"
            ),
            TicketModel(5000,
                Calendar.getInstance(), Calendar.getInstance(),
                "AER", "PRD",
                3.5f,
                false
            )
        ))
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val marginDecoration = VerticalMarginItemDecoration(20f)

        binding.rvTickets.apply {
            adapter = ticketsAdapter
            layoutManager = manager
            addItemDecoration(marginDecoration)
        }
    }

    private fun refreshCities() {
        binding.tvCities.text = resources.getString(R.string.cities_text,
            sharedViewModel.startCity.value,
            sharedViewModel.destination.value
        )
    }
    private fun refreshDetails() {
        binding.tvDetails.text = sharedViewModel.flightDate.value?.let {
            "${DateTimeStringifier().stringifyDateWithoutDayOfWeek(
                it
            )}, ${
                resources.getString(
                    R.string.passengers_count,
                    sharedViewModel.passengersCount.value
                )
            }"
        }
    }
}