package com.project.bibit_test.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.bibit_test.R
import com.project.bibit_test.data.Resource
import com.project.bibit_test.databinding.FragmentHomeBinding
import com.project.bibit_test.ui.adapter.CoinListAdapter
import com.project.bibit_test.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val homeViewModel: HomeViewModel by viewModel()

    private val coinListAdapter by lazy { CoinListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeListener()
        initRecyclerView()
        initTopTierVolumeFull()
    }

    private fun initSwipeListener(){
        binding.swipeView.setOnRefreshListener {
            homeViewModel.topTierVolumeFull.refresh()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeView.isRefreshing = false
            }, 150)
        }
    }

    private fun initRecyclerView(){
        binding.listRecyclerview.adapter = coinListAdapter
        binding.listRecyclerview.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
    }

    private fun initTopTierVolumeFull(){
        homeViewModel.topTierVolumeFull.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    showList()
                    coinListAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    showError()
                    if(it.throwable is UnknownHostException){
                        Toast.makeText(requireContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoading(){
        requireActivity().runOnUiThread {
            binding.animationView.visibility = View.VISIBLE
            binding.listRecyclerview.visibility = View.GONE
            binding.errorMessageView.visibility = View.GONE
        }
    }

    private fun showList(){
        requireActivity().runOnUiThread{
            binding.animationView.visibility = View.GONE
            binding.listRecyclerview.visibility = View.VISIBLE
            binding.errorMessageView.visibility = View.GONE
        }
    }

    private fun showError(){
        requireActivity().runOnUiThread {
            binding.animationView.visibility = View.GONE
            binding.listRecyclerview.visibility = View.GONE
            binding.errorMessageView.visibility = View.VISIBLE
        }
    }
}