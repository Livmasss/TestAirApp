package com.livmas.air_tikets.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.databinding.FragmentHomeBinding
import com.livmas.air_tikets.ui.DestinationPickerBottomSheetDialog
import com.livmas.air_tikets.ui.SearchViewModel
import com.livmas.air_tikets.ui.home.music_adapter.HorizontalMarginItemDecoration
import com.livmas.air_tikets.ui.home.music_adapter.MusicItemModel
import com.livmas.air_tikets.ui.home.music_adapter.MusicRecyclerAdapter
import com.livmas.ui.MyTextWatcher
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : Fragment() {
    private val viewModel: SearchViewModel by activityViewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveStartCity()
    }

    private fun setupViews() {
        setupRecyclerView()
        setupStartCityEditTextView()
        setupDestinationEditTextView()
    }

    private fun setupRecyclerView() {
        val rvAdapter = MusicRecyclerAdapter(listOf(
            MusicItemModel(0, "Дора", "Питер", 5000),
            MusicItemModel(1, "Типы эти", "Москва", 4550),
            MusicItemModel(2, "Лампа бикта", "Ростов на Дону ставит раком всю страну", 7000)
        ))
        val manager = LinearLayoutManager(requireContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL

        binding.rvMusicRecycler.apply{
            adapter = rvAdapter
            layoutManager = manager
            addItemDecoration(HorizontalMarginItemDecoration( 60))
        }
    }

    private fun setupStartCityEditTextView() {
        viewModel.readStartCity()

        binding.etCityFrom.addTextChangedListener(
            MyTextWatcher{ viewModel.startCity.postValue(it) }
        )
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

    private fun setupObservers() {
        setupStartCityObserver()
    }

    private fun setupStartCityObserver() {
        viewModel.initialStartCity.observe(viewLifecycleOwner) {
            binding.etCityFrom.setText(it)
        }
    }
}