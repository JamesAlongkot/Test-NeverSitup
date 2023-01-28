package com.alongkot.testneversitup.ui.history.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class FragmentAdapter(
    fm: FragmentManager?,
    val context: Context,
    private val fragments: ArrayList<Fragment>
) : FragmentPagerAdapter(fm!!,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }
}
