package com.project.bibit_test.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.bibit_test.ui.chat.ChatFragment
import com.project.bibit_test.ui.home.HomeFragment
import com.project.bibit_test.ui.portfolio.PortfolioFragment
import com.project.bibit_test.ui.search.SearchFragment
import com.project.bibit_test.ui.stream.StreamFragment

class MainPagerAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {

    val homeMenuList by lazy {
        listOf(
            HomeFragment(),
            StreamFragment(),
            SearchFragment(),
            ChatFragment(),
            PortfolioFragment()
        )
    }

    override fun getItemCount(): Int {
        return homeMenuList.size
    }

    override fun createFragment(position: Int): Fragment {
        return homeMenuList[position]
    }

    inline fun <reified T>getFragmentPosition(): Int{
        for(index in 0..this.homeMenuList.size){
            if(this.homeMenuList[index] is T){
                return index
            }
        }
        return 0
    }
}