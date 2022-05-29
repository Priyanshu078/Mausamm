package com.priyanshupaliwal.mausamm.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.priyanshupaliwal.mausamm.R
import com.priyanshupaliwal.mausamm.WeatherApplication
import com.priyanshupaliwal.mausamm.adapters.PlaceListAdapter
import com.priyanshupaliwal.mausamm.data.Place
import com.priyanshupaliwal.mausamm.databinding.FragmentBasicWeatherInfoBinding
import com.priyanshupaliwal.mausamm.utility.Utility
import com.priyanshupaliwal.mausamm.viewModel.BasicWeatherInfoViewModel
import com.priyanshupaliwal.mausamm.viewModel.PlaceViewModel
import com.priyanshupaliwal.mausamm.viewModel.PlaceViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class BasicWeatherInfoFragment : Fragment() {

    private var _binding: FragmentBasicWeatherInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BasicWeatherInfoViewModel by viewModels()
    private val placeViewModel : PlaceViewModel by activityViewModels{
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
        _binding = FragmentBasicWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.hide()
        binding.progressCircular.visibility = View.VISIBLE
        checkYourInternetScreen(requireContext())
        val navView = binding.navView
        val headerView = navView.getHeaderView(0)
        val placeRecyclerView:RecyclerView = headerView.findViewById(R.id.placesRecyclerView)
        placeRecyclerView.layoutManager = LinearLayoutManager(view.context)
        Log.d("list",placeViewModel.placesList.toString())
        placeViewModel.allItems.observe(this.viewLifecycleOwner){
            list -> placeViewModel.getPlacesList(list)
            placeRecyclerView.adapter = PlaceListAdapter(placeViewModel.placesList)
        }
        // background color change karne ke liye
//        view.setBackgroundColor(resources.getColor(R.color.black))
        viewModel.data.observe(viewLifecycleOwner) { newData ->
            run {
                binding.progressCircular.visibility = View.GONE
            }
            val timestamp: Long = newData.dt
            val timeD = Date(timestamp * 1000)
            val sdf = SimpleDateFormat("dd MMM yyyy").format(timeD)
            binding.date.text = sdf
            binding.weather.text =
                getString(R.string.temperature, floor(newData.main.temperature).toInt().toString())
            binding.cityName.text = newData.name
            binding.weatherInfo.text = newData.weather[0].main
            binding.details.text = getString(R.string.details)
            binding.humidityText.text = "${newData.main.humidity} %"
            binding.visibilityText.text =
                getString(R.string.visibilityValue, (newData.visibility / 1000).toString())
            binding.pressureText.text =
                getString(R.string.pressureValue, newData.main.pressure.toString())
            binding.windSpeedText.text =
                getString(R.string.windSpeedValue, newData.wind.speed.toString())
            if (newData.weather[0].main == "Clear" || newData.weather[0].main == "clear") {
                displayImage(R.drawable.sunny)
            } else if (newData.weather[0].main == "Rain" || newData.weather[0].main == "rain") {
                displayImage(R.drawable.rain)
            } else if (newData.weather[0].main == "Clouds" || newData.weather[0].main == "clouds") {
                displayImage(R.drawable.cloudy)
            } else {
                displayImage(R.drawable.haze)
            }
            placeViewModel.allItems.observe(this.viewLifecycleOwner) { list ->
                Log.d("dataList", list.toString())
                placeViewModel.getPlacesList(list)
                if (!placeViewModel.presentInDatabase(SearchValue.cityName.lowercase())) {
                    val place = Place(placeName = SearchValue.cityName.lowercase())
                    placeViewModel.insertNewPlace(place)
                }
            }
        }
        binding.details.setOnClickListener {
            if (viewModel.forecastData.value != null) {
                view.findNavController().navigate(
                    BasicWeatherInfoFragmentDirections.actionBasicWeatherInfoFragmentToDetailWeatherInfoFragment(
                        model = viewModel
                    )
                )
            } else {
                Snackbar.make(
                    view.rootView,
                    "Please Wait !!!\nwe are fetching data for you",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        binding.searchImageView.setOnClickListener {
            view.findNavController()
                .navigate(BasicWeatherInfoFragmentDirections.actionBasicWeatherInfoFragmentToSearchFragment())
        }
        binding.drawerIcon.setOnClickListener {
            binding.drawerLayout.open()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayImage(value : Int){
        context?.let {
            Glide.with(it)
                .load(value)
            .into(binding.weatherImage)
        }
    }

    private fun checkYourInternetScreen(context: Context){
        if(!Utility().checkForInternet(context)){
            val action = BasicWeatherInfoFragmentDirections.actionBasicWeatherInfoFragmentToCheckInternetFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}