package com.livmas.air_tikets.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.databinding.FragmentTicketsBinding
import com.livmas.air_tikets.ui.tickets.adapter.TicketModel
import com.livmas.air_tikets.ui.tickets.adapter.TicketsRecyclerAdapter
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import java.util.Calendar

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        val ticketsAdapter = TicketsRecyclerAdapter(listOf(
            TicketModel(5000,
                Calendar.getInstance(), Calendar.getInstance(),
                "AER", "PRD",
                3.5f,
                false
            ),
            TicketModel(5000,
                Calendar.getInstance(), Calendar.getInstance(),
                "AER", "PRD",
                3.5f,
                false
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
}