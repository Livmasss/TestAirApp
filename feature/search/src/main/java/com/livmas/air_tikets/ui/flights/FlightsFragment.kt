package com.livmas.air_tikets.ui.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FragmentFlightsBinding
import com.livmas.air_tikets.ui.SearchViewModel
import com.livmas.air_tikets.ui.flights.adapter.FlightModel
import com.livmas.air_tikets.ui.flights.adapter.FlightsAdapter
import com.livmas.ui.fragemnts.DatePickerFragment
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import com.livmas.utils.DateTimeStringifier
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

internal class FlightsFragment : Fragment() {
    private val viewModel: FlightsViewModel by viewModel()
    private val sharedViewModel: SearchViewModel by activityViewModel()
    private lateinit var binding: FragmentFlightsBinding
    private val dateTimeStringifier = DateTimeStringifier()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateTimeStringifier.secondColor = ContextCompat.getColor(requireContext(), com.livmas.ui.R.color.light_grey)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        setupBackNavigateButton()
        setupSwapButton()
        setupTicketsButton()
        setupRecyclerView()
        setupFlightDateButton()
        setupReturnFlightDateButton()
    }

    private fun setupObservers() {
        setupCitiesObservers()
        setupDatesObservers()
    }

    private fun setupCitiesObservers() {
        sharedViewModel.destination.observe(viewLifecycleOwner) {
            binding.etCityTo.setText(it)
        }
        sharedViewModel._startCity.observe(viewLifecycleOwner) {
            binding.etCityFrom.setText(it)
        }
    }

    private fun setupDatesObservers() {
        viewModel.flightDate.observe(viewLifecycleOwner) {
            it?.let {
                binding.btnFlightDate.text = dateTimeStringifier.stringifyDate(it)
            }
        }
        viewModel.returnFlightDate.observe(viewLifecycleOwner) {
            it?.let {
                binding.btnBackTicket.text = dateTimeStringifier.stringifyDate(it)
            }
        }
    }

    private fun setupRecyclerView() {
        val flightsAdapter = FlightsAdapter(
            requireContext(),
            listOf(
                FlightModel(0, "First", 1234, listOf(Calendar.getInstance(), Calendar.getInstance())),
                FlightModel(1, "Вторая", 10234, listOf(Calendar.getInstance())),
                FlightModel(2, "Компания", 5000, listOf()),
                ),
            dateTimeStringifier
            )
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

    private fun setupBackNavigateButton() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupReturnFlightDateButton() {
        binding.btnBackTicket.setOnClickListener {
            val fragment = DatePickerFragment {
                viewModel.returnFlightDate.postValue(it)
            }
            fragment.show(parentFragmentManager, "datePicker")
        }
    }

    private fun setupFlightDateButton() {
        binding.btnFlightDate.setOnClickListener {
            val fragment = DatePickerFragment {
                viewModel.flightDate.postValue(it)
            }
            fragment.show(parentFragmentManager, "datePicker")
        }
    }

    private fun setupSwapButton() {
        binding.btnSwap.setOnClickListener {
            sharedViewModel.swapFromAndTo()
        }
    }

    private fun setupTicketsButton() {
        binding.btnTickets.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_flights_to_fragment_tickets)
        }
    }
}