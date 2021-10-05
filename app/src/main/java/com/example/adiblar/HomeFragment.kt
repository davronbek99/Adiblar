package com.example.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.adiblar.adapters.ImagePagerAdapter
import com.gauravk.bubblenavigation.BubbleToggleView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
// salom o'zgarish bo'ldi
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var categoryList: ArrayList<String>
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        loadCategories()
        imagePagerAdapter = ImagePagerAdapter(childFragmentManager, categoryList)
        root.view_pager.adapter = imagePagerAdapter
        root.tab_layout.setupWithViewPager(root.view_pager)
        setUpTabItems()
        root.tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val titleCategory = customView?.findViewById<TextView>(R.id.title_category)
                val linearLayout = customView?.findViewById<LinearLayout>(R.id.tab_item_linear)
                titleCategory?.setTextColor(resources.getColor(R.color.white))
                linearLayout?.background = resources.getDrawable(R.drawable.item_background)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val titleCategory = customView?.findViewById<TextView>(R.id.title_category)
                val linearLayout = customView?.findViewById<LinearLayout>(R.id.tab_item_linear)
                titleCategory?.setTextColor(resources.getColor(R.color.black))
                linearLayout?.background = resources.getDrawable(R.drawable.item2)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        root.search_back_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.searchFragment)
        }
        return root
    }

    private fun setUpTabItems() {
        val tabCount = root.tab_layout.tabCount
        for (i in 0 until tabCount) {
            val tabAt = root.tab_layout.getTabAt(i)
            val tabView = layoutInflater.inflate(R.layout.tab_item, null, false)
            val titleCategory = tabView.findViewById<TextView>(R.id.title_category)
            val linear = tabView.findViewById<LinearLayout>(R.id.tab_item_linear)
            titleCategory.text = categoryList[i]
            if (i == 0) {
                titleCategory.setTextColor(resources.getColor(R.color.white))
                linear.background = resources.getDrawable(R.drawable.item_background)
            } else {
                titleCategory.setTextColor(resources.getColor(R.color.black))
                linear.background = resources.getDrawable(R.drawable.item2)
            }
            tabAt?.customView = tabView
        }

    }

    private fun loadCategories() {
        categoryList = ArrayList()
        categoryList.add("Mumtoz adabiyoti")
        categoryList.add("O'zbek adabiyoti")
        categoryList.add("Jahon adabiyoti")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}