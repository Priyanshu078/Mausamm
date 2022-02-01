package com.example.mausam.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mausam.adapters.HourlyForecastAdapter
import com.example.mausam.adapters.WeeklyForecastAdapter
import com.example.mausam.databinding.FragmentDetailWeatherInfoBinding
import com.example.mausam.viewModel.BasicWeatherInfoViewModel

class DetailWeatherInfoFragment : Fragment() {

    private var _binding:FragmentDetailWeatherInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var obj:BasicWeatherInfoViewModel
//    private val viewModel:BasicWeatherInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            obj = it.getParcelable("model")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailWeatherInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("num", obj.forecastData.value?.latitude.toString())
        try {
            binding.hourlyForecastRecyclerView.adapter = HourlyForecastAdapter(obj.forecastData.value?.hourly!!)
            binding.hourlyForecastRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.weeklyForecastRecyclerView.adapter = WeeklyForecastAdapter(obj.forecastData.value?.daily!!)
            binding.weeklyForecastRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        catch (e:Exception){
            e.message?.let { Log.d("ex", it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}