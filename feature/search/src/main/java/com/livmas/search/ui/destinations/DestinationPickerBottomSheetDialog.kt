package com.livmas.search.ui.destinations

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.DestinationPickerBottomsheetFragmentBinding
import com.livmas.search.ui.SearchViewModel
import com.livmas.search.ui.destinations.adapter.RecomendationModel
import com.livmas.search.ui.destinations.adapter.RecomendationsAdapter
import com.livmas.ui.MyTextWatcher
import com.livmas.ui.recycler_decorations.VerticalMarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class DestinationPickerBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding : DestinationPickerBottomsheetFragmentBinding
    private val viewModel: SearchViewModel by activityViewModel()

    companion object {
        var isOpened = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, com.livmas.ui.R.style.Theme_TestAirApp_BottomSheet)
    }

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

    override fun onDestroy() {
        super.onDestroy()
        isOpened = false
    }

    private fun setupViews() {
        setupTabButtons()
        setupStartCityEditTextView()
        setupDestinationEditTextView()
        setupClearButton()
        setupAdapter()
    }

    private fun setupTabButtons() {
        val navController = findNavController()

        binding.apply{
            btnComplexRoute.setOnClickListener {
                navController.navigateFromModal(R.id.action_fragment_home_to_fragment_complex_route)
            }
            btnAnywhere.setOnClickListener {
                val text = resources.getString(R.string.label_anywhere)
                binding.etCityTo.setText(text)
                viewModel.destination.postValue(text)
                navController.navigateFromModal(R.id.action_fragment_home_to_fragment_flights)
            }
            btnWeekend.setOnClickListener {
                navController.navigateFromModal(R.id.action_fragment_home_to_fragment_weekend)
            }
            btnHotTickets.setOnClickListener {
                navController.navigateFromModal(R.id.action_fragment_home_to_fragment_hot_tickets)
            }
        }
    }

    private fun setupStartCityEditTextView() {
        binding.etCityFrom.apply {
            addTextChangedListener(
                MyTextWatcher{ viewModel.postStartCity(it) }
            )
        }
    }

    private fun setupDestinationEditTextView() {
        binding.etCityTo.apply {
            addTextChangedListener(
                MyTextWatcher{ viewModel.destination.postValue(it) }
            )

            setOnKeyListener { _: View?, keyCode: Int, event: KeyEvent ->
                // If the event is a key-down event on the "enter" button
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    findNavController().navigateFromModal(R.id.action_fragment_home_to_fragment_flights)
                    true
                }
                else
                    false
            }
        }
    }

    private fun setupClearButton() {
        binding.btnClearDestination.setOnClickListener {
            binding.etCityTo.setText("")
        }
    }

    private fun setupAdapter() {
        val myAdapter = RecomendationsAdapter(listOf(
            RecomendationModel("Стамбул", resources.getString(R.string.popular_dir_message), R.drawable.istambul),
            RecomendationModel("Сочи", resources.getString(R.string.popular_dir_message), R.drawable.sochi),
            RecomendationModel("Пхукет", resources.getString(R.string.popular_dir_message), R.drawable.phuket),
        )) {
            // OnClickListener
            val tvDestination = it.findViewById<TextView>(R.id.tvTitle)
            binding.etCityTo.setText(tvDestination.text.toString())
            findNavController().navigateFromModal(R.id.action_fragment_home_to_fragment_flights)
        }
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

    private fun NavController.navigateFromModal(actionId: Int) {
        try {
            navigate(actionId)
            dismiss()
        }
        catch (ignore: Exception) {
            // Do thing if navigation fails
        }
    }

    private fun initText() {
        binding.etCityFrom.setText(viewModel._startCity.value)
        binding.etCityTo.setText(viewModel.destination.value)
    }
}