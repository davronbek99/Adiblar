package com.example.adiblar.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BottomNavigationPagerAdapter(
    fragmentManager: FragmentManager,
    var fragmentList: List<Fragment>
) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

}