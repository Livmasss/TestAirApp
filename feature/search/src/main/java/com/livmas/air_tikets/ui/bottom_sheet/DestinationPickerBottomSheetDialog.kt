package com.livmas.air_tikets.ui.bottom_sheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.DestinationPickerBottomsheetFragmentBinding
import com.livmas.air_tikets.ui.SearchViewModel
import com.livmas.air_tikets.ui.bottom_sheet.destination_adapter.DestinationModel
import com.livmas.air_tikets.ui.bottom_sheet.destination_adapter.DestinationsAdapter
import com.livmas.ui.MyTextWatcher
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DestinationPickerBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding : DestinationPickerBottomsheetFragmentBinding
    private val viewModel: SearchViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DestinationPickerBottomsheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheet : FrameLayout = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!

        // Height of the view
        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        // Behavior of the bottom sheet
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.apply {
            peekHeight = resources.displayMetrics.heightPixels // Pop-up height
            state = BottomSheetBehavior.STATE_EXPANDED // Expanded state

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {}

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    override fun onStart() {
        super.onStart()
        initText()
    }

    private fun setupViews() {
        setupTabs()
        setupStartCityEditTextView()
        setupDestinationEditTextView()
        setupClearButton()
        setupAdapter()
    }

    private fun setupTabs() {
        val navController = findNavController()

        binding.apply{
            btnComplexRoute.setOnClickListener {
                navController.navigateToTab(R.id.action_fragment_home_to_fragment_complex_route)
            }
            btnAnywhere.setOnClickListener {
                val text = resources.getString(R.string.label_anywhere)
                binding.etCityTo.setText(text)
                viewModel.destination.postValue(text)
            }
            btnWeekend.setOnClickListener {
                navController.navigateToTab(R.id.action_fragment_home_to_fragment_weekend)
            }
            btnHotTickets.setOnClickListener {
                navController.navigateToTab(R.id.action_fragment_home_to_fragment_hot_tickets)
            }
        }
    }

    private fun setupStartCityEditTextView() {
        binding.etCityFrom.apply {
            addTextChangedListener(
                MyTextWatcher{ viewModel.startCity.postValue(it) }
            )
        }
    }

    private fun setupDestinationEditTextView() {
        binding.etCityTo.apply {
            addTextChangedListener(
                MyTextWatcher{ viewModel.destination.postValue(it) }
            )

            setOnClickListener {
                Log.d("test", "Listener")
                val searchModel = DestinationPickerBottomSheetDialog()

                searchModel.show(parentFragmentManager, "search")
            }
        }
    }

    private fun setupClearButton() {
        binding.btnClearDestination.setOnClickListener {
            binding.etCityTo.setText("")
        }
    }

    private fun setupAdapter() {
        val myAdapter = DestinationsAdapter(listOf(
            DestinationModel("Стамбул", "Популярное направление", R.drawable.istambul),
            DestinationModel("Сочи", "Популярное направление", R.drawable.sochi),
            DestinationModel("Пхукет", "Популярное направление", R.drawable.phuket),
        ))
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val marginDecoration = VerticalMarginItemDecoration(
            15f
        )

        binding.rvDestinations.apply {
            adapter = myAdapter
            layoutManager = manager
            addItemDecoration(dividerDecoration)
            addItemDecoration(marginDecoration)
        }
    }

    private fun NavController.navigateToTab(actionId: Int) {
        try {
            navigate(actionId)
            dismiss()
        }
        catch (e: Exception) {}
    }

    private fun initText() {
        binding.etCityFrom.setText(viewModel.startCity.value)
        binding.etCityTo.setText(viewModel.destination.value)
    }
}