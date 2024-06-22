package com.livmas.search.ui.flights

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
import com.livmas.search.ui.SearchViewModel
import com.livmas.search.ui.flights.adapter.FlightsAdapter
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
    private var rvAdapter: FlightsAdapter? = null
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

        dateTimeStringifier.secondColor = ContextCompat.getColor(requireContext(), com.livmas.ui.R.color.grey_6)

        setupViews()
        setupObservers()
        initiateData()
    }

    private fun setupViews() {
        setupBackNavigateButton()
        setupSwapButton()
        setupClearButton()
        setupTicketsButton()
        setupRecyclerView()
        setupFlightDateButton()
        setupReturnFlightDateButton()
    }

    private fun setupClearButton() {
        binding.btnClearDestination.setOnClickListener {
            sharedViewModel.destination.postValue("")
        }
    }

    private fun setupObservers() {
        setupCitiesObservers()
        setupDatesObservers()
        setupFlightsObserver()
    }

    private fun initiateData() {
        viewModel.refreshFlights()
        sharedViewModel.passengersCount.postValue(1)
    }

    private fun setupFlightsObserver() {
        viewModel.flights.observe(viewLifecycleOwner) {
            if (it == null)
                return@observe

            rvAdapter?.apply {
                updateData(it)
                binding.pbDateLoading.visibility = View.GONE
            }
        }
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
        sharedViewModel.flightDate.observe(viewLifecycleOwner) {
            it?.let {
                binding.btnFlightDate.text = dateTimeStringifier.stringifyDateWithDayOfWeek(it)
            }
        }
        sharedViewModel.returnFlightDate.observe(viewLifecycleOwner) {
            binding.btnBackTicket.text = if (it == null)
                "обратно"
            else
                dateTimeStringifier.stringifyDateWithDayOfWeek(it)
        }
    }

    private fun setupRecyclerView() {
        rvAdapter = FlightsAdapter(
            requireContext(),
            listOf(),
            dateTimeStringifier
        )
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val marginDecoration = VerticalMarginItemDecoration(20f)

        binding.rvFlights.apply {
            adapter = rvAdapter
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

    private fun setupFlightDateButton() {
        binding.btnFlightDate.setOnClickListener {
            val fragment = DatePickerFragment(
                false,
                startDate = sharedViewModel.flightDate.value ?: Calendar.getInstance(),
                minAvailableDate = Calendar.getInstance()
            ) {
                sharedViewModel.flightDate.postValue(it)

                it?.let { flightDate ->
                    sharedViewModel.returnFlightDate.value?.timeInMillis?.let { returnFlightDate ->
                        if (flightDate.timeInMillis > returnFlightDate)
                            sharedViewModel.returnFlightDate.postValue(null)
                    }
                }
            }

            fragment.show(parentFragmentManager, "datePicker")
        }
    }

    private fun setupReturnFlightDateButton() {
        binding.btnBackTicket.setOnClickListener {
            val fragment = DatePickerFragment(
                true,
                startDate = sharedViewModel.returnFlightDate.value ?: Calendar.getInstance(),
                sharedViewModel.flightDate.value
            ) {
                sharedViewModel.returnFlightDate.postValue(it)
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