package com.project.bibit_test.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.project.bibit_test.R
import com.project.bibit_test.databinding.FragmentDashboardBinding
import com.project.bibit_test.helper.avatar.AvatarConstants
import com.project.bibit_test.helper.avatar.AvatarGenerator
import com.project.bibit_test.helper.avatar.StringRandomColor
import com.project.bibit_test.ui.chat.ChatFragment
import com.project.bibit_test.ui.home.HomeFragment
import com.project.bibit_test.ui.logout.LogoutDialog
import com.project.bibit_test.ui.portfolio.PortfolioFragment
import com.project.bibit_test.ui.search.SearchFragment
import com.project.bibit_test.ui.stream.StreamFragment
import com.project.bibit_test.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment: Fragment(R.layout.fragment_dashboard),
    LogoutDialog.LogOutListener, NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private val viewPagerAdapter by lazy { MainPagerAdapter(requireActivity()) }

    private val mainViewModel: MainViewModel by viewModel()

    @SuppressLint("RtlHardcoded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackPressed()
        binding.dashboarViewPager.adapter = viewPagerAdapter
        binding.dashboarViewPager.isUserInputEnabled = false
        binding.navView.setOnNavigationItemSelectedListener(setBottomNavigationListener())
        binding.headerLayout.menuIconView.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }
        initHeaderNavigationView()
        binding.navigationDrawerView.setNavigationItemSelectedListener(this)
    }

    private fun initHeaderNavigationView(){
        mainViewModel.myProfile.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val headerLayout = binding.navigationDrawerView.getHeaderView(0)
                headerLayout?.apply {
                    val username = it.userName?:"USER"
                    val randomColor = StringRandomColor.getColor(username)
                    Glide.with(this).load("")
                        .placeholder(AvatarGenerator.avatarImage(requireContext(), 200, AvatarConstants.CIRCLE, username,
                            Color.parseColor(randomColor))).into(findViewById(R.id.profile_photo_view))
                    findViewById<TextView>(R.id.username_view).text = username
                }
                mainViewModel.myProfile.removeObservers(viewLifecycleOwner)
            }
        })
    }

    private fun setBottomNavigationListener() =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> showFragment<HomeFragment>()
                R.id.navigation_stream -> showFragment<StreamFragment>()
                R.id.navigation_search -> showFragment<SearchFragment>()
                R.id.navigation_chat -> showFragment<ChatFragment>()
                R.id.navigation_portfolio -> showFragment<PortfolioFragment>()
            }
            true
        }

    private inline fun <reified T>showFragment(){
        binding.dashboarViewPager.setCurrentItem(viewPagerAdapter.getFragmentPosition<T>(), false)
    }

    private fun initBackPressed(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun doAfterLogout() {
        findNavController().navigate(R.id.action_navigation_main_to_navigation_login)
    }

    override fun doThirdPartyLogoutAction() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.navigation_log_out){
            LogoutDialog.show(requireActivity().supportFragmentManager, this)
        }
        return true
    }
}