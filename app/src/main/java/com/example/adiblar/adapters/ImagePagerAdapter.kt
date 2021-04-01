package com.example.adiblar.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.adiblar.ImageFragment

class ImagePagerAdapter(fragmentManager: FragmentManager, var categoryList: List<String>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return categoryList.size
    }

//    "https://m3.otkrovenia.com/cache/img/mz/3q/mz3q1b1ho82alkqflsn.jpg"
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ImageFragment.newInstance("mumtoz")
            }
            1 -> {
                ImageFragment.newInstance("uzbek")
            }
            else -> {
                ImageFragment.newInstance("jahon")
            }
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryList[position]
    }

}