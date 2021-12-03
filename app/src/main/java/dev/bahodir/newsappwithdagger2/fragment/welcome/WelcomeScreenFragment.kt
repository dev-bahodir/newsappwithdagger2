package dev.bahodir.newsappwithdagger2.fragment.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.cachememory.cache1.CacheBool
import dev.bahodir.newsappwithdagger2.databinding.FragmentWelcomeScreenBinding
import dev.bahodir.newsappwithdagger2.fragment.welcome.adapter.WelcomeScreenVpAdapter
import kotlin.math.abs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeScreenFragment : Fragment() {
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

    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var welcomeScreenVpAdapter: WelcomeScreenVpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        val arr = arrayListOf(R.drawable.img1, R.drawable.img2, R.drawable.img3)
        welcomeScreenVpAdapter = WelcomeScreenVpAdapter(arr)
        binding.viewTransition.adapter = welcomeScreenVpAdapter
        binding.dotsIndicator.setViewPager2(binding.viewTransition)

        binding.next.setOnClickListener {

            if (binding.viewTransition.currentItem == 2) {
                binding.title.text = "Nuntium"
                binding.dotsIndicator.visibility = View.INVISIBLE
                binding.viewTransition.visibility = View.INVISIBLE
                binding.next.visibility = View.INVISIBLE

                binding.imageview.visibility = View.VISIBLE
                binding.start.visibility = View.VISIBLE
            }
            binding.viewTransition.currentItem++
        }

        binding.viewTransition.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    binding.title.text = "First to know"
                } else if (position == 1) {
                    binding.title.text = "Second to know"
                } else if (position == 2) {
                    binding.title.text = "Third to know"
                }
            }
        })

        binding.start.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_selectFragment)
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        binding.viewTransition.clipToPadding = false
        binding.viewTransition.clipChildren = false
        binding.viewTransition.offscreenPageLimit = 3
        binding.viewTransition.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.viewTransition.setPageTransformer(compositePageTransformer)


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
         * @return A new instance of fragment WelcomeScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}