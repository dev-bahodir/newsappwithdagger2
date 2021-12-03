package dev.bahodir.newsappwithdagger2.di.component

import dagger.Component
import dev.bahodir.newsappwithdagger2.ui.view.MainActivity
import dev.bahodir.newsappwithdagger2.di.module.DatabaseModule
import dev.bahodir.newsappwithdagger2.di.module.NetworkModule
import dev.bahodir.newsappwithdagger2.fragment.home.browse.BrowseItemVpFragment
import dev.bahodir.newsappwithdagger2.fragment.home.infragment.BookmarksFragment
import dev.bahodir.newsappwithdagger2.fragment.home.infragment.BrowseFragment
import dev.bahodir.newsappwithdagger2.fragment.info.InfoFragment
import dev.bahodir.newsappwithdagger2.fragment.seeall.SeeAllFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun mainActivityInject(mainActivity: MainActivity)
    fun browseItemVpFragment(browseItemVpFragment: BrowseItemVpFragment)
    fun browseFragment(browseFragment: BrowseFragment)
    fun bookmarksFragment(bookmarksFragment: BookmarksFragment)
    fun seeAll(seeAllFragment: SeeAllFragment)
    fun info(infoFragment: InfoFragment)
}