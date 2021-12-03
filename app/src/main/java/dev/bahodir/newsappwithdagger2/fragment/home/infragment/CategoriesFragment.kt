package dev.bahodir.newsappwithdagger2.fragment.home.infragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.cachememory.cache1.CacheBool
import dev.bahodir.newsappwithdagger2.databinding.FragmentCategoriesBinding
import dev.bahodir.newsappwithdagger2.fragment.select.adapter.SelectRvAdapter
import dev.bahodir.newsappwithdagger2.fragment.select.model.SelectUser
import dev.bahodir.newsappwithdagger2.fragment.select.room.SelectDB
import dev.bahodir.newsappwithdagger2.fragment.select.room.SelectDao

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
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

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectList: List<SelectUser>
    private lateinit var list: ArrayList<SelectUser>
    private lateinit var selectRvAdapter: SelectRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        selectList = SelectDB.getInstance(requireContext()).selectDao().getList()
        loadItem()

        selectRvAdapter =
            SelectRvAdapter(requireContext(), list, object : SelectRvAdapter.OnSelectItem {
                override fun selectItem(selectUser: SelectUser, position: Int) {
                    if (selectUser.bool) {
                        SelectDB.getInstance(requireContext()).selectDao().add(selectUser)
                    } else if (!selectUser.bool) {
                        SelectDB.getInstance(requireContext()).selectDao().deleteById(selectUser.id)
                    }
                }

            })
        binding.recycler.adapter = selectRvAdapter

        return binding.root
    }

    private fun loadItem() {
        list = ArrayList()
        list.add(SelectUser(id = 1, tv = "\uD83C\uDFC8 Sports", bool = false))
        list.add(SelectUser(id = 2, tv = "⚖ Politics", bool = false))
        list.add(SelectUser(id = 3, tv = "\uD83C\uDF1E Life", bool = false))
        list.add(SelectUser(id = 4, tv = "\uD83C\uDFAE Gaming", bool = false))
        list.add(SelectUser(id = 5, tv = "\uD83D\uDC3B Animals", bool = false))
        list.add(SelectUser(id = 6, tv = "\uD83C\uDF34 Nature", bool = false))
        list.add(SelectUser(id = 7, tv = "\uD83C\uDF54 Food", bool = false))
        list.add(SelectUser(id = 8, tv = "\uD83C\uDFA8 Art", bool = false))
        list.add(SelectUser(id = 9, tv = "\uD83D\uDCDC History", bool = false))
        list.add(SelectUser(id = 10, tv = "\uD83D\uDC57 Fashion", bool = false))
        list.add(SelectUser(id = 11, tv = "\uD83D\uDE37 Covid-19", bool = false))
        list.add(SelectUser(id = 12, tv = "⚔ Middle East", bool = false))

        for (i in list.indices) {
            for (j in selectList.indices) {
                if (list[i].id == selectList[j].id)
                    list[i].bool = true
            }
        }
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
         * @return A new instance of fragment CategoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}