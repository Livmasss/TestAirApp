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

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
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

    private fun setupViews() {
        setupRecyclerView()
        setupFromEditTextView()
        setupToEditTextView()
    }
    private fun setupRecyclerView() {
        val rvAdapter = MusicRecyclerAdapter(listOf(
            MusicItemModel(0, "Дора", "Питер", 5000),
            MusicItemModel(1, "Типы эти", "Москва", 4550),
            MusicItemModel(2, "Лампа бикта", "Росв на Дону ставит раком всю страну", 7000)
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

    }

    private fun setupToEditTextView() {

    }
}