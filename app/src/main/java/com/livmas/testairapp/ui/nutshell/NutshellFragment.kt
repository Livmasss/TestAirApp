package com.livmas.testairapp.ui.nutshell

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.livmas.testairapp.R

class NutshellFragment : Fragment() {

    companion object {
        fun newInstance() = NutshellFragment()
    }

    private lateinit var viewModel: NutshellViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nutshell, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NutshellViewModel::class.java)
        // TODO: Use the ViewModel
    }

}