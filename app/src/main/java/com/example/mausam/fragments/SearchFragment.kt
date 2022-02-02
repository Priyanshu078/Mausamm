package com.example.mausam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.mausam.R
import com.example.mausam.databinding.FragmentSearchBinding

object SearchValue{
    var cityName :String = ""
}

class SearchFragment : Fragment() {


    private var _binding:FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.outlinedButton.setOnClickListener {
            if(binding.outlinedTextField.editText?.text!!.toString() != "") {
                SearchValue.cityName = binding.outlinedTextField.editText?.text!!.toString()
                view.findNavController()
                    .navigate(SearchFragmentDirections.actionSearchFragmentToBasicWeatherInfoFragment())
            }
            else{
                Toast.makeText(context,"Please type the city name",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}