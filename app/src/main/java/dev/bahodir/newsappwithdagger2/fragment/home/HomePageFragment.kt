package dev.bahodir.newsappwithdagger2.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yariksoffice.lingver.Lingver
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.cachememory.cache2.CacheTheme
import dev.bahodir.newsappwithdagger2.cachememory.cache3.CacheLang
import dev.bahodir.newsappwithdagger2.databinding.FragmentHomePageBinding
import dev.bahodir.newsappwithdagger2.fragment.home.infragment.BookmarksFragment
import dev.bahodir.newsappwithdagger2.fragment.home.infragment.BrowseFragment
import dev.bahodir.newsappwithdagger2.fragment.home.infragment.CategoriesFragment
import dev.bahodir.newsappwithdagger2.fragment.home.infragment.SettingsFragment
import dev.bahodir.newsappwithdagger2.fragment.select.room.SelectDB
import dev.bahodir.newsappwithdagger2.theme.ThemeHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var _binding : FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val TAG = "Create"
    private lateinit var cacheTheme: CacheTheme
    private lateinit var cacheLang: CacheLang

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        cacheTheme = CacheTheme(requireContext())
        cacheLang = CacheLang(requireContext())

        if (cacheTheme.getCacheBool()) {
            ThemeHelper.applyTheme(ThemeHelper.darkMode)
        }
        else {
            ThemeHelper.applyTheme(ThemeHelper.lightMode)
        }

        Lingver.getInstance().setLocale(
            requireContext(), cacheLang.getCacheBool() ?: "en"
        )

        //binding.bottomView.menu.getItem(0).isCheckable = true

        success(0)
        setFragment(BrowseFragment())

        binding.bottomView.setOnItemSelectedListener {menu ->

            when(menu.itemId){

                R.id.browse -> {
                    setFragment(BrowseFragment())
                    true
                }

                R.id.categories -> {
                    setFragment(CategoriesFragment())
                    true
                }

                R.id.bookmarks -> {
                    setFragment(BookmarksFragment())
                    true
                }

                R.id.settings -> {
                    setFragment(SettingsFragment())
                    true
                }

                else -> false
            }
        }

        return binding.root
    }
    fun success(i: Int) {
        binding.bottomView.menu.getItem(i).isCheckable = true
    }

    private fun setFragment(fragment: Fragment){
        val frag = requireActivity().supportFragmentManager.beginTransaction()
        frag.replace(R.id.frame,fragment)
        frag.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomePageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}