package dev.bahodir.newsappwithdagger2.adapter.browse

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.bahodir.newsappwithdagger2.fragment.home.browse.BrowseItemVpFragment

class BrowseVpAdapter(var fragmentActivity: FragmentActivity, var list: List<String>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return BrowseItemVpFragment.newInstance(list[position])
    }
}