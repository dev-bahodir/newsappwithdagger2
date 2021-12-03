package dev.bahodir.newsappwithdagger2.fragment.seeall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.adapter.browse.BrowseRvAdapter
import dev.bahodir.newsappwithdagger2.app.App
import dev.bahodir.newsappwithdagger2.databinding.FragmentSeeAllBinding
import dev.bahodir.newsappwithdagger2.fragment.select.model.SelectUser
import dev.bahodir.newsappwithdagger2.fragment.select.room.SelectDB
import dev.bahodir.newsappwithdagger2.model.Article
import dev.bahodir.newsappwithdagger2.ui.main.NewsResource
import dev.bahodir.newsappwithdagger2.ui.view.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SeeAllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SeeAllFragment : Fragment(), CoroutineScope {
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
    private var _binding: FragmentSeeAllBinding? = null
    private val binding get() = _binding!!

    private lateinit var list: List<SelectUser>
    private val TAG = "BrowseFragment"
    private lateinit var allList: ArrayList<Article>
    private lateinit var browseRvAdapter: BrowseRvAdapter

    @Inject
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        App.appComponent.seeAll(this)
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        list = SelectDB.getInstance(requireContext()).selectDao().getList()

        browseRvAdapter = BrowseRvAdapter(object : BrowseRvAdapter.OnClickListener {
            override fun itemClick(article: Article, position: Int) {
                val bundle = Bundle()
                bundle.putSerializable("article", article)
                findNavController().navigate(R.id.action_homePageFragment_to_infoFragment, bundle)
            }

        })
        binding.recycler.adapter = browseRvAdapter

        allList = ArrayList()
        for (i in list.indices) {
            val sp = list[i].tv.split(" ")
            launch {
                newsViewModel
                    .getNewsApi(sp[1])
                    .collect {
                        when (it) {
                            is NewsResource.Loading -> {
                                binding.recycler.visibility = View.INVISIBLE
                                binding.pro.visibility = View.VISIBLE
                                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            is NewsResource.Error -> {
                                binding.recycler.visibility = View.INVISIBLE
                                binding.pro.visibility = View.VISIBLE
                                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                            }
                            is NewsResource.Success -> {
                                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT)
                                    .show()
                                binding.recycler.visibility = View.VISIBLE
                                binding.pro.visibility = View.GONE

                                for (j in it.newsApi?.articles?.indices!!) {
                                    it.newsApi?.articles?.get(j)?.main = list[i].tv
                                    it.newsApi?.articles?.get(j)?.let { its -> allList.add(its) }
                                }
                                allList.shuffle()
                                browseRvAdapter.submitList(allList)
                            }
                        }
                    }
            }
        }
        return binding.root
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
         * @return A new instance of fragment SeeAllFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SeeAllFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}