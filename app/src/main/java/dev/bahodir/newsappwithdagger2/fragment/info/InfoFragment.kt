package dev.bahodir.newsappwithdagger2.fragment.info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.app.App
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.databinding.FragmentInfoBinding
import dev.bahodir.newsappwithdagger2.model.Article
import dev.bahodir.newsappwithdagger2.ui.view.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM = "article"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getSerializable(ARG_PARAM) as Article?
        }
    }

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private var bool = false

    @Inject
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        App.appComponent.info(this)
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        binding.sourceNameTv.text = article?.main
        binding.newsDataTv.text = article?.description
        binding.titleTv.text = article?.title
        Picasso.get().load(article?.urlToImage).into(binding.image)
        Picasso.get().load(article?.urlToImage).into(binding.imageOnBg)

        binding.backImg.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.shareImg.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, article?.description)
            shareIntent.putExtra(Intent.EXTRA_TITLE, article?.title)
            startActivity(Intent.createChooser(shareIntent, "Share"))
        }
        val saveEntity = SaveEntity(
            article?.publishedAt.toString(),
            article?.urlToImage.toString(),
            article?.main.toString(),
            article?.description.toString()
        )

        binding.saveImg.setOnClickListener {
            launch {
                if (!bool) {
                    bool = true
                    binding.saveImg.setAltImageResource(R.drawable.ic_bookmark_all_color)
                    binding.saveImg.setImageResource(R.drawable.ic_bookmark_all_white)
                    newsViewModel.addArticleVM(saveEntity)
                }
                else {
                    bool = false
                    binding.saveImg.setAltImageResource(R.drawable.ic_save)
                    binding.saveImg.setImageResource(R.drawable.ic_save_white)
                    newsViewModel.deleteArticleVM(saveEntity)
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
         * @param article Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(article: Article) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM, article)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}