package dev.bahodir.newsappwithdagger2.language

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yariksoffice.lingver.Lingver
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.cachememory.cache3.CacheLang
import dev.bahodir.newsappwithdagger2.databinding.FragmentLanguageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LanguageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LanguageFragment : Fragment() {
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

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!
    private lateinit var cacheLang: CacheLang

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)

        cacheLang = CacheLang(requireContext())

        binding.en.setOnClickListener {
            binding.en.setBackgroundResource(R.drawable.next_back)
            binding.en.setTextColor(Color.WHITE)

            binding.uz.setBackgroundResource(R.drawable.white_back)
            binding.uz.setTextColor(Color.BLACK)

            cacheLang.setCacheBool("en")

            Lingver.getInstance().setLocale(
                requireContext(), cacheLang.getCacheBool() ?: "en"
            )
        }

        binding.uz.setOnClickListener {
            binding.en.setBackgroundResource(R.drawable.white_back)
            binding.en.setTextColor(Color.BLACK)

            binding.uz.setBackgroundResource(R.drawable.next_back)
            binding.uz.setTextColor(Color.WHITE)

            cacheLang.setCacheBool("uz")

            Lingver.getInstance().setLocale(
                requireContext(), cacheLang.getCacheBool() ?: "uz"
            )
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
         * @return A new instance of fragment LanguageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LanguageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}