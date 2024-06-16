package com.livmas.testairapp.ui.subscribes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.livmas.testairapp.R

class SubscribesFragment : Fragment() {

    companion object {
        fun newInstance() = SubscribesFragment()
    }

    private lateinit var viewModel: SubscribesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subscribes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubscribesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}