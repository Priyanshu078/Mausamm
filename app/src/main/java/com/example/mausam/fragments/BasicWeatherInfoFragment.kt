package com.example.mausam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mausam.R
import com.example.mausam.databinding.FragmentBasicWeatherInfoBinding
import com.example.mausam.viewModel.BasicWeatherInfoViewModel
import kotlin.math.floor

class BasicWeatherInfoFragment : Fragment() {

    private var _binding: FragmentBasicWeatherInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BasicWeatherInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasicWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressCircular.visibility = View.VISIBLE
        activity?.actionBar?.hide()
        // background color change karne ke liye
//        view.setBackgroundColor(resources.getColor(R.color.black))
        viewModel.data.observe(viewLifecycleOwner, { newData ->
            run {
                binding.progressCircular.visibility = View.GONE
            }
            binding.weather.text =
                getString(R.string.temperature, floor(newData.main.temperature).toInt().toString())
            binding.cityName.text = newData.name
            binding.weatherInfo.text = newData.weather[0].main
            binding.details.text = getString(R.string.details)
            binding.humidityText.text = "${newData.main.humidity} %"
            binding.visibilityText.text = getString(R.string.visibilityValue,(newData.visibility/1000).toString())
            binding.pressureText.text = getString(R.string.pressureValue,newData.main.pressure.toString())
            binding.windSpeedText.text = getString(R.string.windSpeedValue,newData.wind.speed.toString())
        })
        binding.details.setOnClickListener {
            if (viewModel.forecastData.value != null) {
                view.findNavController().navigate(
                    BasicWeatherInfoFragmentDirections.actionBasicWeatherInfoFragmentToDetailWeatherInfoFragment(
                        model = viewModel
                    )
                )
            }
            else{
                Toast.makeText(
                    context,
                    "Please Wait !!!\nwe are fetching data for you",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}