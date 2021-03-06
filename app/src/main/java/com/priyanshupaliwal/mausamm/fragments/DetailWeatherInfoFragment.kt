package com.priyanshupaliwal.mausamm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.priyanshupaliwal.mausamm.adapters.HourlyForecastAdapter
import com.priyanshupaliwal.mausamm.adapters.WeeklyForecastAdapter
import com.priyanshupaliwal.mausamm.databinding.FragmentDetailWeatherInfoBinding
import com.priyanshupaliwal.mausamm.viewModel.BasicWeatherInfoViewModel

class DetailWeatherInfoFragment : Fragment() {
    private val navigationArgs: DetailWeatherInfoFragmentArgs by navArgs()
    private var _binding:FragmentDetailWeatherInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var obj:BasicWeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        obj = navigationArgs.model
        Log.d("num", obj.forecastData.value?.latitude.toString())
            binding.hourlyForecastRecyclerView.adapter = HourlyForecastAdapter(requireContext(),obj.forecastData.value?.hourly!!)
            binding.hourlyForecastRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.weeklyForecastRecyclerView.adapter = WeeklyForecastAdapter(requireContext(),obj.forecastData.value?.daily!!)
            binding.weeklyForecastRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}