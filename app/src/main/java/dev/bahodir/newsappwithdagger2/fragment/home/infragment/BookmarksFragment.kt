package dev.bahodir.newsappwithdagger2.fragment.home.infragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.adapter.bookmarks.BookmarksRvAdapter
import dev.bahodir.newsappwithdagger2.adapter.browse.BrowseRvAdapter
import dev.bahodir.newsappwithdagger2.app.App
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.databinding.FragmentBookmarksBinding
import dev.bahodir.newsappwithdagger2.model.Article
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
 * Use the [BookmarksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarksFragment : Fragment(), CoroutineScope {
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
    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookmarksRvAdapter: BookmarksRvAdapter

    @Inject
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        App.appComponent.bookmarksFragment(this)
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)

        bookmarksRvAdapter = BookmarksRvAdapter(object : BookmarksRvAdapter.OnClickListener {
            override fun itemClick(save: SaveEntity, position: Int) {

            }

        })
        binding.recycler.adapter = bookmarksRvAdapter

        launch {
           newsViewModel.getArticleVM().collect {
               if (it.isNotEmpty()) {
                   binding.recycler.visibility = View.VISIBLE
                   binding.defaultImage.visibility = View.INVISIBLE
                   binding.defaultInfo.visibility = View.INVISIBLE
               }

               bookmarksRvAdapter.submitList(it)
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
         * @return A new instance of fragment BookmarksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookmarksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}