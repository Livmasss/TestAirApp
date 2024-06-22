package com.livmas.search.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FragmentTicketsBinding
import com.livmas.search.ui.SearchViewModel
import com.livmas.search.ui.tickets.adapter.TicketsRecyclerAdapter
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import com.livmas.utils.DateTimeStringifier
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment() {
    private val viewModel: TicketsViewModel by viewModel()
    private val sharedViewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: FragmentTicketsBinding
    private var rvAdapter: TicketsRecyclerAdapter? = null

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
        setupObservers()
        refreshValues()
    }

    private fun setupViews() {
        setupRecyclerView()
        setupBackButton()
    }

    private fun setupObservers() {
        setupTicketsObserver()
    }

    private fun refreshValues() {
        refreshCities()
        refreshDetails()
        viewModel.refreshTickets()
    }

    private fun setupTicketsObserver() {
        viewModel.tickets.observe(viewLifecycleOwner) {
            if (it == null)
                return@observe

            rvAdapter?.apply {
                updateData(it)
                binding.pbDateLoading.visibility = View.GONE
            }
        }
    }

    private fun setupBackButton() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun setupRecyclerView() {
        rvAdapter = TicketsRecyclerAdapter(requireContext(),
            listOf()
        )
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val marginDecoration = VerticalMarginItemDecoration(20f)

        binding.rvTickets.apply {
            adapter = rvAdapter
            layoutManager = manager
            addItemDecoration(marginDecoration)
        }
    }

    private fun refreshCities() {
        binding.tvCities.text = resources.getString(
            R.string.cities_text,
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