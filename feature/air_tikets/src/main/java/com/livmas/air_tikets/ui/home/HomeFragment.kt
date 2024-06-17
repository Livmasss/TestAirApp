package com.livmas.air_tikets.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.air_tikets.databinding.FragmentHomeBinding
import com.livmas.air_tikets.ui.home.music_adapter.HorizontalMarginItemDecoration
import com.livmas.air_tikets.ui.home.music_adapter.MusicItemModel
import com.livmas.air_tikets.ui.home.music_adapter.MusicRecyclerAdapter
import com.livmas.ui.MyTextWatcher
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()
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
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveStartCity()
    }

    private fun setupViews() {
        setupRecyclerView()
        setupFromEditTextView()
        setupToEditTextView()
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

    private fun setupFromEditTextView() {
        binding.etCityFrom.addTextChangedListener(
            MyTextWatcher{ viewModel.startCity.postValue(it) }
        )
    }

    private fun setupToEditTextView() {
        binding.etCityTo.addTextChangedListener(
            MyTextWatcher{ viewModel.destination.postValue(it) }
        )
    }
}