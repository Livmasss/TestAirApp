package com.livmas.search.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.databinding.FragmentHomeBinding
import com.livmas.search.ui.SearchViewModel
import com.livmas.search.ui.destinations.DestinationPickerBottomSheetDialog
import com.livmas.search.ui.home.music_adapter.FeedRecyclerAdapter
import com.livmas.ui.MyTextWatcher
import com.livmas.ui.recycler_decorations.HorizontalMarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()
    private val sharedViewModel: SearchViewModel by activityViewModel()
    private lateinit var binding: FragmentHomeBinding
    private var rvAdapter: FeedRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (sharedViewModel.destination.value != null)
            showDialog()

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        initiateData()
    }

    override fun onStop() {
        super.onStop()
        sharedViewModel.saveStartCity()
    }

    private fun setupViews() {
        setupRecyclerView()
        setupStartCityEditTextView()
        setupDestinationEditTextView()
    }

    private fun initiateData() {
        viewModel.refreshFeed()
    }

    private fun setupRecyclerView() {
        rvAdapter = FeedRecyclerAdapter(
            listOf()
        )
        val manager = LinearLayoutManager(requireContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL

        binding.rvMusicRecycler.apply{
            adapter = rvAdapter
            layoutManager = manager
            addItemDecoration(HorizontalMarginItemDecoration(60f))
        }
    }

    private fun setupStartCityEditTextView() {
        sharedViewModel.readStartCity()

        binding.etCityFrom.addTextChangedListener(
            MyTextWatcher{
                sharedViewModel.postStartCity(it)
            }
        )
    }

    private fun setupDestinationEditTextView() {
        binding.etCityTo.apply {
            setOnClickListener {
                showDialog()
            }
        }
    }

    private fun setupObservers() {
        setupStartCityObserver()
        setupDestinationObserver()
        setupFeedObserver()
    }

    private fun setupFeedObserver() {
        viewModel.feed.observe(viewLifecycleOwner) {
            if (it == null)
                return@observe

            rvAdapter?.updateDate(it)
        }
    }

    private fun setupStartCityObserver() {
        sharedViewModel.initialStartCity.observe(viewLifecycleOwner) {
            if (it != null) {
                sharedViewModel.initialStartCity.removeObservers(viewLifecycleOwner)
                binding.etCityFrom.setText(it)
            }
        }
    }

    private fun setupDestinationObserver() {
        sharedViewModel.destination.observe(viewLifecycleOwner) {
            binding.etCityTo.setText(it)
        }
    }

    private fun showDialog() {
        if (DestinationPickerBottomSheetDialog.isOpened)
            return
        DestinationPickerBottomSheetDialog().show(parentFragmentManager, "search")
        DestinationPickerBottomSheetDialog.isOpened = true
    }
}