package com.bangkit.usergithub.adapter

import android.os.Bundle
import android.content.Context
import com.bangkit.usergithub.R
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bangkit.usergithub.ui.FollowersFragment
import com.bangkit.usergithub.ui.FollowingFragment

class SectionPagerAdapter(private val mContext: Context, fManager: FragmentManager, data: Bundle) : FragmentPagerAdapter(fManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle = data

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_a, R.string.tab_b)

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}
