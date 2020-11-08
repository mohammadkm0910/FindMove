package com.mohammad.kk.findmove.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerMethodAdapter(fm: FragmentManager, behavior: Int = 0) : FragmentPagerAdapter(fm, behavior) {
    private val fragList:MutableList<Fragment> = ArrayList()
    private val fragTitleList:MutableList<String> = ArrayList()
    override fun getCount(): Int {
        return fragList.size
    }
    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }
    fun addFragment(fragment: Fragment,title:String) {
        fragList.add(fragment)
        fragTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragTitleList[position]
    }

}