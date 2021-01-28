package com.project.bibit_test.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.project.bibit_test.R
import com.project.bibit_test.databinding.FragmentEmptyBinding
import com.project.bibit_test.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_empty) {

    private val binding by viewBinding(FragmentEmptyBinding::bind)

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })
    }
}