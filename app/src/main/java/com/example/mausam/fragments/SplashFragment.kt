package com.example.mausam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.mausam.WeatherApplication
import com.example.mausam.databinding.FragmentSplashBinding
import com.example.mausam.viewModel.PlaceViewModel
import com.example.mausam.viewModel.PlaceViewModelFactory

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel : PlaceViewModel by activityViewModels{
        PlaceViewModelFactory(
            (activity?.application as WeatherApplication).database.placeDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allItems.observe(this.viewLifecycleOwner){
                list -> viewModel.getPlacesList(list)
            if(list.isNotEmpty()) {
                SearchValue.cityName = viewModel.placesList[0]
                view.findNavController()
                    .navigate(SplashFragmentDirections.actionSplashFragmentToBasicWeatherInfoFragment())
            }
            else{
                view.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSearchFragment())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}