package com.priyanshupaliwal.mausamm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.priyanshupaliwal.mausamm.databinding.FragmentCheckInternetBinding
import com.priyanshupaliwal.mausamm.utility.Utility
import com.google.android.material.snackbar.Snackbar
import java.time.Duration

class CheckInternetFragment : Fragment() {
    private var _binding: FragmentCheckInternetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckInternetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tryAgainButton.setOnClickListener {
            if(Utility().checkForInternet(requireContext())) {
                val action =
                    CheckInternetFragmentDirections.actionCheckInternetFragmentToBasicWeatherInfoFragment()
                view.findNavController().navigate(action)
            }
            else{
                Snackbar.make(view.rootView,"Please check your internet connection",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}