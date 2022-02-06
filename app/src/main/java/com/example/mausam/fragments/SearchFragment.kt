package com.example.mausam.fragments

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.mausam.R
import com.example.mausam.databinding.FragmentSearchBinding
import org.w3c.dom.Text
import java.util.*

object SearchValue{
    var cityName :String = ""
}

class SearchFragment : Fragment() {


    private var _binding:FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var textToSpeech: TextToSpeech

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
        binding.imageButton.setOnClickListener {
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}