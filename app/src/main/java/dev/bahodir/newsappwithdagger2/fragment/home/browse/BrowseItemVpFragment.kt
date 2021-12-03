package dev.bahodir.newsappwithdagger2.fragment.home.browse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.adapter.bookmarks.BrowseItemVpFragmentRvAdapter
import dev.bahodir.newsappwithdagger2.app.App
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.databinding.FragmentBrowseItemVpBinding
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
private const val ARG_PARAM = "param"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BrowseItemVpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowseItemVpFragment : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param: String? = null
    //private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentBrowseItemVpBinding? = null
    private val binding get() = _binding!!
    private lateinit var browseItemVpFragmentRvAdapter: BrowseItemVpFragmentRvAdapter
    private val TAG = "BrowseItemVpFragment"

    @Inject
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        App.appComponent.browseItemVpFragment(this)
        _binding = FragmentBrowseItemVpBinding.inflate(inflater, container, false)

        browseItemVpFragmentRvAdapter = BrowseItemVpFragmentRvAdapter(
            object : BrowseItemVpFragmentRvAdapter.OnClickListener {
                override fun saveClick(saveEntity: SaveEntity, position: Int, bool: Boolean) {
                    Toast.makeText(requireContext(), "$bool", Toast.LENGTH_SHORT).show()

                    launch {
                        if (bool) {
                            newsViewModel.addArticleVM(saveEntity)
                        }
                        else {
                            newsViewModel.deleteArticleVM(saveEntity)
                        }
                    }
                }

                override fun itemClick(article: Article, position: Int) {
                    val bundle = Bundle()
                    bundle.putSerializable("article", article)
                    findNavController().navigate(
                        R.id.action_homePageFragment_to_infoFragment,
                        bundle
                    )
                }

            })
        binding.recycler.adapter = browseItemVpFragmentRvAdapter

        launch {
            newsViewModel
                .getNewsApi(param.toString())
                .collect {
                    when (it) {
                        is NewsResource.Loading -> {
                            Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                            binding.recycler.visibility = View.INVISIBLE
                            binding.pro.visibility = View.VISIBLE
                        }
                        is NewsResource.Error -> {
                            binding.recycler.visibility = View.INVISIBLE
                            binding.pro.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                        }
                        is NewsResource.Success -> {
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            binding.recycler.visibility = View.VISIBLE
                            binding.pro.visibility = View.GONE

                            for (i in it.newsApi?.articles?.indices!!) {
                                it.newsApi?.articles!![i].main = param.toString()
                            }

                            browseItemVpFragmentRvAdapter.submitList(it.newsApi?.articles)
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
         * @param param Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BrowseItemVpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param: String) =
            BrowseItemVpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}