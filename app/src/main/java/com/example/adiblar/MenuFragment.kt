package com.example.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.adiblar.adapters.BottomNavigationPagerAdapter
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var root: View
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var bottomNavigationPagerAdapter: BottomNavigationPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_menu, container, false)
        loadCategories()

        bottomNavigationPagerAdapter =
            BottomNavigationPagerAdapter(childFragmentManager, fragmentList)
        root.menu_view_pager.adapter = bottomNavigationPagerAdapter
        root.bottom_navigation_view_linear.setNavigationChangeListener { view, position ->
            root.menu_view_pager.setCurrentItem(position)
        }

        root.menu_view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                root.bottom_navigation_view_linear.setCurrentActiveItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


        return root
    }

    private fun loadCategories() {
        fragmentList = ArrayList()
        fragmentList.add(HomeFragment())
        fragmentList.add(SaqlanganFragment())
        fragmentList.add(SettingsFragment())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}