package dev.bahodir.newsappwithdagger2.fragment.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.cachememory.cache1.CacheBool
import dev.bahodir.newsappwithdagger2.databinding.FragmentSplashBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
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
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var cacheBool: CacheBool

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        cacheBool = CacheBool(requireContext())

        object : CountDownTimer(2000, 100) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (cacheBool.getCacheBool()) {
                    findNavController().navigate(R.id.action_splashFragment_to_homePageFragment)
                }
                else {
                    findNavController().navigate(R.id.action_splashFragment_to_welcomeScreenFragment)
                }
            }

        }.start()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}